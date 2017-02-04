package jp.co.rakus.bbs.web;

import lombok.Data;
/**
 * 記事削除フォームを定義
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Data
public class DeleteForm {
	/**
	 * 削除する記事の記事id
	 */
	private String articleId;
	
	/**
	 * フォームから送られたString型の記事idをInteger形に変換
	 * Integer型に変換できないときは例外を発生させる
	 * @param articleId　String型
	 * @return IntArticleId Integer型
	 * @throws NumberFormatException
	 */
	public Integer getIntArticleId(String articleId) throws NumberFormatException{
		return Integer.parseInt(articleId);
	}
}
