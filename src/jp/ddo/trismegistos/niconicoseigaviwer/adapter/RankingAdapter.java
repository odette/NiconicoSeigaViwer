
package jp.ddo.trismegistos.niconicoseigaviwer.adapter;

import java.util.List;

import jp.ddo.trismegistos.androidutil.view.WebImageView;
import jp.ddo.trismegistos.androidutil.view.helper.ImageCache;
import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.ds.dto.SeigaInfoDto;
import jp.ddo.trismegistos.niconicoseigaviwer.util.ImageUtil;
import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * ランキングを表示するためのAdapterクラス。
 * 
 * @author y_sugasawa
 * @since 2013/01/29
 */
public class RankingAdapter extends ArrayAdapter<SeigaInfoDto> {

    /** コンテキスト。 */
    private Context context;

    /** inflater。 */
    private LayoutInflater inflater;

    /** 1行のリソースID。 */
    private int resourceId;

    /** 表示するItem。 */
    private List<SeigaInfoDto> items;

    /** イメージキャッシュ。 */
    private ImageCache imageCache;

    /**
     * コンストラクタ。
     * 
     * @param context コンテキスト
     * @param resourceId 1行のリソースID
     * @param items 表示するItem
     */
    public RankingAdapter(final Context context, final int resourceId,
            final List<SeigaInfoDto> items) {
        super(context, resourceId, items);

        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resourceId;
        this.items = items;

        // ImageCache作成
        final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        imageCache = new ImageCache(ImageUtil.getCacheDir(context), 1024 * 1024 * memClass / 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourceId, null);
            holder = new ViewHolder();
            holder.rank = (TextView) convertView.findViewById(R.id.rank);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.image = (WebImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SeigaInfoDto dto = items.get(position);
        holder.rank.setText(dto.rank);
        holder.title.setText(dto.title);
        holder.nickname.setText(dto.nickname);
        holder.image.setUrl(ImageUtil.getSmallImg(dto.id));
        holder.image.setImageCache(imageCache);
        holder.image.show();

        return convertView;
    }

    public static class ViewHolder {
        public TextView rank;
        public TextView title;
        public TextView nickname;
        public WebImageView image;
    }

    /**
     * itemを入れ替える。
     * 
     * @param items 表示するItem
     */
    public void setItems(final List<SeigaInfoDto> items) {
        this.items.clear();
        this.items.addAll(items);
    }
}
