/**
 * 簡易テーブルモデル.
 * 行データ配列にたいして、表示開始位置を指定することで表示情報のほかにキーなどの管理情報を
 * 保持できる。
 * 
 * @version $Revision: 1.1 $ $Date: 2003/01/28 11:59:21 $
 */
package components;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class SimpleTableModel extends AbstractTableModel {
/* -------------------------------------------------------------------
 フィールド
------------------------------------------------------------------- */
	/** カラム名 */
	protected Vector columnName = new Vector();
	/** データ */
	protected Vector data = new Vector();
	/** データ表示開始カラム */
	protected int startColumn;
/* -------------------------------------------------------------------
 データ表示開始カラム指定
------------------------------------------------------------------- */
	/**
	 * データ表示開始カラム指定
	 *
	 * @param	c	データ表示開始カラム
	 */
	public void setStartColumn(int c) {
		startColumn = c;
	}
/* -------------------------------------------------------------------
 カラム名設定
------------------------------------------------------------------- */
	/**
	 * カラム名設定
	 *
	 * @param	columnName	カラム名
	 */
	public void setColumnNames(String[] columnName) {
		this.columnName.clear();
		for(int i = 0; i < columnName.length; i++) {
			this.columnName.add(columnName[i]);
		}
		fireTableStructureChanged();
	}
	/**
	 * カラム名指定
	 *
	 * @param	columnName	カラム名
	 */
	public void setColumnNames(Vector columnName) {
		this.columnName = columnName;
		fireTableStructureChanged();
	}
/* -------------------------------------------------------------------
 データ設定
------------------------------------------------------------------- */
	/**
	 * データ設定
	 *
	 * @param	data	データ
	 */
	public void setData(Object[][] data) {
		clear();
		for(int i = 0; i < data.length; i++) {
			this.data.add(data[i]);
		}
		fireTableDataChanged();
	}
	/**
	 * データ追加
	 *
	 * @param	data	データ
	 */
	public void addRow(Object[] data) {
		this.data.add(data);
		fireTableDataChanged();
	}
	/**
	 * データ追加
	 *
	 * @param	data	データ
	 */
	public void addRow(Vector data) {
		if(data.size() > 0) {
			Object[] o = data.toArray();
			addRow(o);
			fireTableDataChanged();
		}
	}
/* -------------------------------------------------------------------
 行数取得
------------------------------------------------------------------- */
	/**
	 * 行数取得
	 *
	 * @return	行数
	 */
	public int getRowCount() {
		return data.size();
	}
/* -------------------------------------------------------------------
 カラム数取得
------------------------------------------------------------------- */
	/**
	 * カラム数取得
	 *
	 * @return	カラム数
	 */
	public int getColumnCount() {
		return columnName.size();
	}
/* -------------------------------------------------------------------
 カラム名取得
------------------------------------------------------------------- */
	/**
	 * カラム名取得
	 *
	 * @param	column	カラム番号
	 * @return			カラム名
	 */
	public String getColumnName(int column) {
		return columnName.get(column).toString();
	}
/* -------------------------------------------------------------------
 セル情報取得
------------------------------------------------------------------- */
	/**
	 * セル情報取得.
	 * 指定した行と列にあるセルの値を返す
	 *
	 * @param	row		行
	 * @param	column	列
	 * @return			オブジェクト
	 */
	public Object getValueAt(int row, int column) {
		return getData(row, column + startColumn);
	}
/* -------------------------------------------------------------------
 データクリア
------------------------------------------------------------------- */
	/**
	 * データクリア.
	 * 全ての行情報をクリアする。
	 */
	public void clear() {
		data.clear();
		fireTableDataChanged();
	}
/* -------------------------------------------------------------------
 データ取得
------------------------------------------------------------------- */
	/**
	 * データ取得.
	 * データ表示開始カラム値を無視して直接データを取得する。
	 *
	 * @param	row		行
	 * @param	column	列
	 * @return			オブジェクト
	 */
	public Object getData(int row, int column) {
		if(row < data.size()) {
			Object[] o = (Object[])data.get(row);
			if(column < o.length) {
				return o[column];
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
/* -------------------------------------------------------------------
 行の取得
------------------------------------------------------------------- */
	/**
	 * 行の取得
	 *
	 * @param	row		行番号
	 * @return			行データ
	 */
	public Object[] getRow(int row) {
		if(row < data.size()) {
			return (Object[])data.get(row);
		} else {
			return null;
		}
	}
/* -------------------------------------------------------------------
 行の設定
------------------------------------------------------------------- */
	/**
	 * 行の設定
	 *
	 * @param	row		行番号
	 * @param	value	行データ
	 */
	public void setRow(int row, Object[] value) {
		if(row < data.size()) {
			data.setElementAt(value, row);
		}
		fireTableDataChanged();
	}
/* -------------------------------------------------------------------
 行の削除
------------------------------------------------------------------- */
	/**
	 * 行の削除
	 *
	 * @param row		行番号
	 */
	public void removeRow(int row){
		Object[] obj = getRow(row);
		if(obj == null){
			return;
		}
		data.remove(row);
		fireTableDataChanged();
	}
	/**
	 * 行の削除
	 *
	 * @param row		行番号
	 */
	public void removeRow(int[] row){
		Collection c = new Vector();
		for(int i = 0; i < row.length; i++){
			Object[] obj = getRow(row[i]);
			c.add(obj);
		}
		data.removeAll(c);
		fireTableDataChanged();
	}
}
