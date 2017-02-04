package jp.co.rakus.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 投稿記事クラス(Beans)
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	/**
	 * 記事id(DBで自動採番)
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
	
	//---------勉強用----------
	//一般的なのは、Articleオブジェクトのフィールドにコメントのリストを用意するやり方
	// 「一対多　Spring jdbc join」ってキーワードでググるといいよ(by 本橋先生)
	//
	//List<Comment> comments;
	//------------------------
	
	
	/**
	 * idを引数に持たないコンストラクタ
	 * @param name 投稿者名
	 * @param content 投稿内容
	 */
	public Article(String name ,String content){
		this.id = null;
		this.name = name;
		this.content = content;
	}

}
