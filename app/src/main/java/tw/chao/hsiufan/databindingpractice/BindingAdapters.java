package tw.chao.hsiufan.databindingpractice;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("app:hideIfZero")
    public static void hideIfZero(View view, Integer number) {
        view.setVisibility(number == 0 ? View.GONE : View.VISIBLE);
    }

    /**
     *  Sets the value of the progress bar so that 5 likes will fill it up.
     *
     *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     *  whenever any of the attribute changes.
     */
    @BindingAdapter(value = {"app:progressScaled", "android:max"}, requireAll = true)
    public static void setProgress(ProgressBar progressBar, Integer likes, Integer max) {
        progressBar.setProgress(Math.min(likes * max / 5, max));
    }

    /**
     * A Binding Adapter that is called whenever the value of the attribute `app:popularityIcon`
     * changes. Receives a popularity level that determines the icon and tint color to use.
     */
    @BindingAdapter("app:popularityIcon")
    public static void popularityIcon(ImageView view, SimpleViewModelSolution.Popularity popularity) {

        int color = getAssociatedColor(popularity, view.getContext());

        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color));

        view.setImageDrawable(getDrawablePopularity(popularity, view.getContext()));
    }

    /**
     * A Binding Adapter that is called whenever the value of the attribute `android:progressTint`
     * changes. Depending on the value it determines the color of the progress bar.
     */
    @BindingAdapter("app:progressTint")
    public static void tintPopularity(ProgressBar view, SimpleViewModelSolution.Popularity popularity) {

        int color = getAssociatedColor(popularity, view.getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setProgressTintList(ColorStateList.valueOf(color));
        }
    }

    private static int getAssociatedColor(SimpleViewModelSolution.Popularity popularity, Context context) {

        if (popularity.equals(SimpleViewModelSolution.Popularity.NORMAL)) {
            return context.getTheme().obtainStyledAttributes(
                    new int[] {android.R.attr.colorForeground}).getColor(0, 0x000000);
        } else if (popularity.equals(SimpleViewModelSolution.Popularity.POPULAR)) {
            return ContextCompat.getColor(context, R.color.popular);
        } else {
            return ContextCompat.getColor(context, R.color.star);
        }
    }

    private static Drawable getDrawablePopularity(SimpleViewModelSolution.Popularity popularity, Context context) {

        if (popularity.equals(SimpleViewModelSolution.Popularity.NORMAL)) {
            return ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp);
        } else if (popularity.equals(SimpleViewModelSolution.Popularity.POPULAR)) {
            return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
        } else {
            return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
        }
    }
}
