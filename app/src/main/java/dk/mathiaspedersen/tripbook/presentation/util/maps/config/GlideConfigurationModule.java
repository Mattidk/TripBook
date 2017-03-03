package dk.mathiaspedersen.tripbook.presentation.util.maps.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import dk.mathiaspedersen.tripbook.presentation.util.maps.map.Map;

public class GlideConfigurationModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(Map.class, InputStream.class, new ModelLoaderFactory<Map, InputStream>() {
            @Override
            public ModelLoader<Map, InputStream> build(Context context, GenericLoaderFactory factories) {
                return new MapModelLoader(context);
            }

            @Override
            public void teardown() {
                //nothing to do
            }
        });
    }
}
