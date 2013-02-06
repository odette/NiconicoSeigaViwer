package jp.ddo.trismegistos.niconicoseigaviwer.adapter;

import java.util.List;

import jp.ddo.trismegistos.androidutil.ui.WebImageView;
import jp.ddo.trismegistos.androidutil.ui.helper.ImageCache;
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
 * @author y_sugasawa
 * @since 2013/01/29
 */
public class RankingAdapter extends ArrayAdapter<SeigaInfoDto> {

	private Context context;

	private LayoutInflater inflater;

	private int resourceId;

	private List<SeigaInfoDto> items;

	private ImageCache imageCache;

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
		holder.image.draw();

		return convertView;
	}

	public static class ViewHolder {
		public TextView rank;
		public TextView title;
		public TextView nickname;
		public WebImageView image;
	}

	public void setItems(final List<SeigaInfoDto> items) {
		this.items.clear();
		this.items.addAll(items);
	}
}
