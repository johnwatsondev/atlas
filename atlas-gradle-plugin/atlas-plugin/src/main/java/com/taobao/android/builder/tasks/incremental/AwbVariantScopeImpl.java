package com.taobao.android.builder.tasks.incremental;

import java.io.File;
import java.util.Collection;

import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.build.gradle.internal.scope.GlobalScope;
import com.android.build.gradle.internal.scope.VariantScopeImpl;
import com.android.build.gradle.internal.variant.BaseVariantData;
import com.android.build.gradle.internal.variant.BaseVariantOutputData;
import com.android.utils.FileUtils;
import com.google.common.collect.ImmutableList;
import com.taobao.android.builder.dependency.model.AwbBundle;

/**
 * Created by chenhjohn on 2017/6/15.
 */

public class AwbVariantScopeImpl extends VariantScopeImpl {
    private final AwbBundle awbBundle;

    public AwbVariantScopeImpl(GlobalScope globalScope, TransformManager transformManager,
                               BaseVariantData<? extends BaseVariantOutputData> variantData, AwbBundle awbBundle) {
        super(globalScope, transformManager, variantData);
        this.awbBundle = awbBundle;
    }

    @Override
    public String getTaskName(String prefix, String suffix) {
        return super.getTaskName(prefix, suffix) + "ForAwb" + awbBundle.getName();
    }

    @Override
    public Collection<String> getDirectorySegments() {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        builder.add(awbBundle.getName());
        builder.addAll(super.getDirectorySegments());
        return builder.build();
    }

    @Override
    public File getPreDexOutputDir() {
        File preDexOutputDir = super.getPreDexOutputDir();
        return FileUtils.join(preDexOutputDir.getParentFile(), awbBundle.getName(), preDexOutputDir.getName());
    }
}
