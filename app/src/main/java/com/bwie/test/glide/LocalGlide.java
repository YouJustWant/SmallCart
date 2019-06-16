package com.bwie.test.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Author:杨帅
 * Date:2019/6/15 16:18
 * Description：
 */
@GlideModule
public class LocalGlide extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        ExternalPreferredCacheDiskCacheFactory cacheDiskCacheFactory = new ExternalPreferredCacheDiskCacheFactory(context, "YourShop", memoryCacheSizeBytes);
        builder.setDiskCache(cacheDiskCacheFactory);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
