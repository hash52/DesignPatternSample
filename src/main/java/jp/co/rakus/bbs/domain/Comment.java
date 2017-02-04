package jp.co.rakus.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * コメントクラス(Beans)
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	/**
	 * コメントid(DBで自動採番)
	 */
	private Integer id;
	/**
	 * 投稿者名
	 */
	private String name;
	/**
	 * 投稿内容
	 */
	private String content;
	/**
	 * コメントする記事のid
	 */
	private Integer articleId;
	
	/**
	 * コメントidを引数に持たないコンストラクタ
	 * @param name
	 * @param content
	 * @param articleId
	 */
	public Comment(String name ,String content ,Integer articleId){
		this.id = null;
		this.name = name;
		this.content = content;
		this.articleId = articleId;
	}

}
