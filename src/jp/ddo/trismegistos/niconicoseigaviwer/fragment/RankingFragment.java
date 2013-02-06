package jp.ddo.trismegistos.niconicoseigaviwer.fragment;

import java.util.ArrayList;
import java.util.List;

import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.activity.DispActivity;
import jp.ddo.trismegistos.niconicoseigaviwer.adapter.RankingAdapter;
import jp.ddo.trismegistos.niconicoseigaviwer.ds.dto.SeigaInfoDto;
import jp.ddo.trismegistos.niconicoseigaviwer.loader.RankingLoader;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * ランキング表示用Fragment。
 * 
 * @author y_sugasawa
 * @since 2013/01/29
 */
public class RankingFragment extends ListFragment implements LoaderCallbacks<List<SeigaInfoDto>> {
	// ------------------------------------------------------------------
	// カスタムビューを利用する場合は以下の定数は必須（変更不可）
	// ------------------------------------------------------------------
	private static final int INTERNAL_EMPTY_ID = 0x00ff0001;
	private static final int INTERNAL_PROGRESS_CONTAINER_ID = 0x00ff0002;
	private static final int INTERNAL_LIST_CONTAINER_ID = 0x00ff0003;

	private RankingAdapter adapter;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			final Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_ranking, container, false);

		// ------------------------------------------------------------------
		// カスタムビューを利用する場合は以下の処理は必須（変更不可）
		// ------------------------------------------------------------------
		final ProgressBar pBar = (ProgressBar) view.findViewById(android.R.id.progress);
		final LinearLayout pframe = (LinearLayout) pBar.getParent();
		pframe.setId(INTERNAL_PROGRESS_CONTAINER_ID);
		pframe.setOrientation(LinearLayout.VERTICAL);
		pframe.setVisibility(View.GONE);
		pframe.setGravity(Gravity.CENTER);

		final ListView listView = (ListView) view.findViewById(android.R.id.list);
		listView.setDrawSelectorOnTop(false);
		listView.setItemsCanFocus(false);
		listView.setScrollingCacheEnabled(false);
		final FrameLayout lFrame = (FrameLayout) listView.getParent();
		lFrame.setId(INTERNAL_LIST_CONTAINER_ID);

		TextView tv = (TextView) view.findViewById(android.R.id.empty);
		tv.setId(INTERNAL_EMPTY_ID);
		tv.setGravity(Gravity.CENTER);
		// ------------------------------------------------------------------

		return view;
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Data");
		// 空のアダプターを作成
		adapter = new RankingAdapter(getActivity(), R.layout.ranking_row,
				new ArrayList<SeigaInfoDto>());
		setListAdapter(adapter);
		// リストを隠してProgressBarを出す
		setListShown(false);
		// ローダーを準備
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onListItemClick(final ListView l, final View v, final int position, final long id) {
		final SeigaInfoDto dto = adapter.getItem(position);
		final Intent intent = new Intent(getActivity(), DispActivity.class);
		intent.putExtra(DispActivity.ARGS_IMG_TITLE, dto.title);
		intent.putExtra(DispActivity.ARGS_IMG_ID, dto.id);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Loader<List<SeigaInfoDto>> onCreateLoader(final int id, final Bundle ars) {
		return new RankingLoader(getActivity());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLoadFinished(final Loader<List<SeigaInfoDto>> loader,
			final List<SeigaInfoDto> data) {
		adapter.setItems(data);
		adapter.notifyDataSetChanged();
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLoaderReset(final Loader<List<SeigaInfoDto>> loader) {
	}
}
