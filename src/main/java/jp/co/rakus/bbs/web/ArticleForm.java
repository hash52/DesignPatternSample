package jp.co.rakus.bbs.web;

import lombok.Data;
/**
 * 記事投稿フォームを定義
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Data
public class ArticleForm {
	/**
	 * 投稿者名
	 */
	private String name;
	/**
	 * 投稿内容
	 */
	private String content;
}
