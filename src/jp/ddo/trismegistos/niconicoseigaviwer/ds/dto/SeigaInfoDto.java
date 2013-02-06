package jp.ddo.trismegistos.niconicoseigaviwer.ds.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author y_sugasawa
 * @since 2013/01/29
 */
public class SeigaInfoDto implements Parcelable {

	/** 順位 */
	public String rank;

	/** id */
	public String id;

	/** タイトル */
	public String title;

	/** 作者名 */
	public String nickname;

	public SeigaInfoDto() {
	}

	public SeigaInfoDto(final Parcel in) {
		rank = in.readString();
		id = in.readString();
		title = in.readString();
		nickname = in.readString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeString(rank);
		dest.writeString(id);
		dest.writeString(title);
		dest.writeString(nickname);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int describeContents() {
		return 0;
	}
}
