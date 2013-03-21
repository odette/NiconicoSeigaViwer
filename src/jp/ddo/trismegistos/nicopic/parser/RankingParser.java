
package jp.ddo.trismegistos.nicopic.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jp.ddo.trismegistos.androidhttp.exception.ApiParseException;
import jp.ddo.trismegistos.androidhttp.parser.Parser;
import jp.ddo.trismegistos.nicopic.ds.dto.SeigaInfoDto;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * ニコニコ静画APIのParserクラス。
 * 
 * @author y_sugasawa
 * @since 2013/01/30
 */
public class RankingParser implements Parser<List<SeigaInfoDto>> {

    /** タグ。 */
    private static final String TAG = RankingParser.class.getSimpleName();

    private static final String TAG_IMAGE = "image";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_NICKNAME = "nickname";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SeigaInfoDto> parse(final String data) throws ApiParseException {

        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(data));
        } catch (final XmlPullParserException e) {
            throw new ApiParseException(e);
        }

        final List<SeigaInfoDto> items = new ArrayList<SeigaInfoDto>();
        try {
            int eventType;
            eventType = xmlPullParser.getEventType();
            SeigaInfoDto dto = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tagName = xmlPullParser.getName();
                        if (TAG_ID.equals(tagName)) {
                            dto = new SeigaInfoDto();
                            dto.id = xmlPullParser.nextText();
                        } else if (TAG_TITLE.equals(tagName)) {
                            String[] title = split(xmlPullParser.nextText());
                            dto.rank = title[0];
                            dto.title = title[1];
                        } else if (TAG_NICKNAME.equals(tagName)) {
                            dto.nickname = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xmlPullParser.getName();
                        if (TAG_IMAGE.equals(tagName)) {
                            items.add(dto);
                        }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (final Exception e) {
            throw new ApiParseException(e);
        }
        return items;
    }

    private String[] split(final String str) {
        return str.split("位 ");
    }
}
