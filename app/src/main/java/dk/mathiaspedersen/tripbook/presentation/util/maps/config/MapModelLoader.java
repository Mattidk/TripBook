package dk.mathiaspedersen.tripbook.presentation.util.maps.config;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import dk.mathiaspedersen.tripbook.presentation.util.maps.map.Map;

public class MapModelLoader extends BaseGlideUrlLoader<Map> {

    public MapModelLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(Map model, int width, int height) {
        int scale = model.scale();

        // if default scale, use scale(2) to get crisper images.
        // scale(4) is reserved to premium static map users.
        if (scale == 1) {
            model.scale(scale = 2);
        }

        return model.size(width / scale, height / scale).toString();
    }
}
