package dk.mathiaspedersen.tripbook.presentation.util.staticmaps.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap;

public class GlideConfigurationModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(StaticMap.class, InputStream.class, new ModelLoaderFactory<StaticMap, InputStream>() {
            @Override
            public ModelLoader<StaticMap, InputStream> build(Context context, GenericLoaderFactory factories) {
                return new MapModelLoader(context);
            }

            @Override
            public void teardown() {
                //nothing to do
            }
        });
    }
}
