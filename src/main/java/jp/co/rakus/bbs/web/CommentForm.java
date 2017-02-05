package jp.co.rakus.bbs.web;

import lombok.Data;
/**
 * コメント投稿フォームを定義
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Data
public class CommentForm {
	/**
	 * 投稿者名
	 */
	private String name;
	/**
	 * 投稿内容
	 */
	private String content;
	/**
	 * コメントを投稿する記事の記事id
	 */
	private String articleId;
	
	/**
	 * フォームから送られたString型の記事idをInteger形に変換
	 * Integer型に変換できないときは例外を発生させる
	 * @param articleId　String型
	 * @return IntArticleId Integer型
	 * @throws NumberFormatException
	 */
	public Integer getIntArticleId(String articleId)throws NumberFormatException{
		return Integer.parseInt(articleId);
	}
	
}
