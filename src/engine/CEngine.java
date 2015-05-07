/**
 * ソースのステップ数、コメント行、空行をカウントするエンジンです。
 *
 */
package engine;

import java.io.*;

public class CEngine implements Engineable{
/* ---------------------------------------------------------
 メイン
--------------------------------------------------------- */
	public static void main(String[] args) throws Exception{
		File src = new File("t.c");
		Engineable engine = new CEngine(src);
		engine.parse();
		System.out.println("総行数:" + engine.getLinage());
		System.out.println("ステップ:" + engine.getStep());
		System.out.println("コメント:" + engine.getComment());
		System.out.println("空:" + engine.getEmpty());
	}
/* ---------------------------------------------------------
 定数
--------------------------------------------------------- */
	/** C++コメント */
	public static final String c_single = "//";
	/** Cコメント開始 */
	public static final String c_s = "/*";
	/** Cコメント終了 */
	public static final String c_e = "*/";

/* ---------------------------------------------------------
 変数
--------------------------------------------------------- */
	/** Cソースファイル */
	private File src;

	/** 総行数 */
	private int linage;
	/** コメントフラグ */
	private boolean c_flag;
	/** ステップ数 */
	private int step;
	/** コメント数 */
	private int comment;
	/** 空行数 */
	private int empty;
	/** printfメソッド数 */
	private int print;

	/** 変更フラグ */
	private boolean v_c_flag;
	/** 変更行数 */
	private int change;

/* ---------------------------------------------------------
 コンストラクタ
--------------------------------------------------------- */
	/**
	 * 解析するファイルを指定して CEngine インスタンスを生成します。
	 *
	 * @param src		Cソースファイル
	 */
	public CEngine(File src){
		this.src = src;
	}
/* ---------------------------------------------------------
 解析
--------------------------------------------------------- */
	/**
	 * 解析します。
	 *
	 * @throws Exception	解析中にエラーが発生した場合
	 */
	public void parse() throws Exception{
		int single;
		BufferedReader br = new BufferedReader(new FileReader(src));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.trim();
			// 総行数
			linage++;

			// コメント継続判断
			if(c_flag){
				comment++;
				if(line.indexOf(c_e) != -1){
					c_flag = false;
				}
				continue;
			}
			// コメント行
			if(line.indexOf(c_s) == 0){
				c_flag = true;
				if(line.indexOf(c_e) != -1){
					c_flag = false;
				}
				comment++;
				continue;
			}
			// 単一コメント
			if((single = line.indexOf(c_single)) != -1){
				if(single == 0){
					comment++;
					continue;
				}
			}
			// 空行
			if(line == null || line.length() == 0){
				empty++;
				continue;
			}
			// ステップ行
			step++;

		}
	}
/* ---------------------------------------------------------
 総行数
--------------------------------------------------------- */
	/**
	 * 総行数を取得します。
	 *
	 * @return 総行数
	 */
	public int getLinage(){
		return linage;
	}
/* ---------------------------------------------------------
 ステップ数取得
--------------------------------------------------------- */
	/**
	 * ステップ数を取得します。
	 *
	 * @return ステップ数
	 */
	public int getStep(){
		return step;
	}
/* ---------------------------------------------------------
 コメント行取得
--------------------------------------------------------- */
	/**
	 * コメント行取得
	 *
	 * @return コメント行
	 */
	public int getComment(){
		return comment;
	}
/* ---------------------------------------------------------
 空行取得
--------------------------------------------------------- */
	/**
	 * 空行取得
	 *
	 * @return 空行
	 */
	public int getEmpty(){
		return empty;
	}
/* ---------------------------------------------------------
 printメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 printfメソッド取得
	 *
	 * @return printfメソッド数
	 */
	public int getPrint(){
		return 0;
//		return print;
	}
/* ---------------------------------------------------------
 トレースメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 トレースメソッド取得
	 *
	 * @return トレースメソッド数
	 */
	public int getTrace(){
		return 0;
	}
/* ---------------------------------------------------------
 変更取得
--------------------------------------------------------- */
	/**
	 * 変更取得
	 *
	 * @return 変更行
	 */
	public int getChange(){
		return 0;
	}

}
