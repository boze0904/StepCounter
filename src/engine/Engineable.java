/**
 * 解析エンジンのインターフェースです。
 *
 */
package engine;

public interface Engineable{
/* ---------------------------------------------------------
 解析
--------------------------------------------------------- */
	/**
	 * 解析します。
	 *
	 * @throws Exception	解析中にエラーが発生した場合
	 */
	public void parse() throws Exception;
/* ---------------------------------------------------------
 総行数
--------------------------------------------------------- */
	/**
	 * 総行数を取得します。
	 *
	 * @return 総行数
	 */
	public int getLinage();
/* ---------------------------------------------------------
 ステップ数取得
--------------------------------------------------------- */
	/**
	 * ステップ数を取得します。
	 *
	 * @return ステップ数
	 */
	public int getStep();
/* ---------------------------------------------------------
 コメント行取得
--------------------------------------------------------- */
	/**
	 * コメント行取得
	 *
	 * @return コメント行
	 */
	public int getComment();
/* ---------------------------------------------------------
 空行取得
--------------------------------------------------------- */
	/**
	 * 空行取得
	 *
	 * @return 空行
	 */
	public int getEmpty();
/* ---------------------------------------------------------
 printメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 printメソッド取得
	 *
	 * @return printメソッド
	 */
	public int getPrint();
/* ---------------------------------------------------------
 トレースメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 トレースメソッド取得
	 *
	 * @return トレースメソッド
	 */
	public int getTrace();
/* ---------------------------------------------------------
 変更取得
--------------------------------------------------------- */
	/**
	 * 変更取得
	 *
	 * @return 変更行
	 */
	public int getChange();
}
