package jp.co.rakus.bbs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.bbs.domain.Article;
import jp.co.rakus.bbs.domain.Comment;
import jp.co.rakus.bbs.service.BbsService;

/**
 * コントローラークラス
 * 
 * @author HirokiHashi 所属：株式会社ラクス 作成日：2016.11.14
 *
 */
@Controller
@RequestMapping("/bbs")
public class BbsController {
	@Autowired
	private BbsService service;

	/**
	 * 記事投稿フォームを初期化
	 * 
	 * @return new ArticleForm()
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	/**
	 * コメント投稿フォームを初期化
	 * 
	 * @return new CommentForm()
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 削除フォームを初期化
	 * 
	 * @return new DeleteForm()
	 */
	@ModelAttribute
	public DeleteForm setUpDeleteForm() {
		return new DeleteForm();
	}

	/**
	 * DBから記事を取得してリクエストスコープに格納し、bbs.jspへ遷移
	 * 
	 * @param model
	 * @return bbs.jsp
	 */
	@RequestMapping("/view")
	public String view(Model model) {
		List<Article> articleList = service.findAllArticle();
		List<Comment> commentList = service.findAllComments();
		model.addAttribute("articleList", articleList);
		model.addAttribute("commentList", commentList);
		return "bbs";
	}

	/**
	 * 記事投稿フォームから記事を投稿して表示ページへリダイレクト
	 * 名前か投稿内容、どちらかが未入力であれば投稿せずにエラーメッセージを格納して表示ページへリダイレクト
	 * 
	 * @param form(ArticleForm)
	 * @param model
	 * @return
	 */
	@RequestMapping("/writearticle")
	public String writeArticle(ArticleForm form, Model model, RedirectAttributes redirectAttributes) {
		// 名前が未入力の時
		if (form.getName().isEmpty()) {
			redirectAttributes.addFlashAttribute("emptyNameMessage", "名前が未入力です");
		}
		// 投稿内容が未入力の時
		if (form.getContent().isEmpty()) {
			redirectAttributes.addFlashAttribute("emptyContentMessage", "投稿内容が未入力です");
		}
		// どちらも入力されているとき
		if (!(form.getName().isEmpty() || form.getContent().isEmpty())) {
			service.insertArticle(new Article(form.getName(), form.getContent()));
		}
		return "redirect:view";
	}

	/**
	 * コメント投稿フォームからコメントを投稿して表示ページへリダイレクト 不正な記事idが渡されれば、何もせずに表示ページへリダイレクト
	 * 名前か投稿内容、どちらかが未入力であれば投稿せずにエラーメッセージを格納して表示ページへリダイレクト
	 * 
	 * @param form(CommentForm)
	 * @param model
	 * @return
	 */
	@RequestMapping("/writecomment")
	public String writeComment(CommentForm form, Model model, RedirectAttributes redirectAttributes) {
		//エラー処理（名前が未入力or投稿内容が未入力）
		if (form.getName().isEmpty() || form.getContent().isEmpty()) {
			//記事番号をセット
			redirectAttributes.addFlashAttribute("articleIdOfEmptyCommentForm", form.getArticleId());
			// 名前が未入力の時
			if (form.getName().isEmpty()) {
				redirectAttributes.addFlashAttribute("emptyCommentNameMessage", "名前が未入力です");
			}
			// 投稿内容が未入力の時
			if (form.getContent().isEmpty()) {
				redirectAttributes.addFlashAttribute("emptyCommentContentMessage", "投稿内容が未入力です");
			}
		}

		// 正常処理(名前と投稿内容が入力されているとき）
		else {
			try {
				service.insertComment(
						new Comment(form.getName(), form.getContent(), form.getIntArticleId(form.getArticleId())));
			} catch (NumberFormatException e) {
			}
		}
		return "redirect:view";
	}

	/**
	 * 削除ボタンが押されるとhiddenで送られた記事idを元に記事と付随するコメントを削除。
	 * 不正な記事idが渡されれば、何もせずに表示ページへリダイレクト
	 * 
	 * @param form(DeleteForm)
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteArticle(DeleteForm form, Model model) {
		try {
			service.deleteArticle(form.getIntArticleId(form.getArticleId()));
		} catch (NumberFormatException e) {
		}
		return "redirect:view";

	}

}