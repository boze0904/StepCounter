/**
 * ��̓��C����ʂł��B
 *
 */

import components.*;
import chooser.*;
import engine.*;
import util.*;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class SrcCounter extends BasicFrame{
/* -------------------------------------------------------------------
 ���C��
------------------------------------------------------------------- */
	public static void main(String[] args){
		new SrcCounter();
	}
/* -------------------------------------------------------------------
 �R���|�[�l���g
------------------------------------------------------------------- */
	/** ���j���[�o�[ */
	private JMenuBar menubar = new JMenuBar();
	/** ���j���[ */
	private JMenu menu = new JMenu("�t�@�C��");
	/** ���j���[�A�C�e�� �J�� */
	private JMenuItem openItem = new JMenuItem("�J��");
	/** ���j���[�A�C�e�� CSV�o�� */
	private JMenuItem csvItem = new JMenuItem("CSV�o��");
	/** ���j���[�A�C�e�� �I�� */
	private JMenuItem closeItem = new JMenuItem("�I��");
	/** �V���v���e�[�u�� */
	protected SimpleTable table = new SimpleTable();
/* -------------------------------------------------------------------
 �ϐ�
------------------------------------------------------------------- */
	/** �f�t�H���g�̃t�H���g��ύX */
	static { UI.setDefaultFont(); }
	/** �J������ */
	protected String[] columnName = {"�\�[�X�t�@�C��", "���s��", "�X�e�b�v�s��", "�R�����g�s��", "��s��",
										"Print���\�b�h", "�g���[�X���\�b�h", "�ύX"};
	/** �J������������ */
	protected int[] columnWidth = {300, 100, 100, 100, 100, 100, 100, 100};

/* -------------------------------------------------------------------
 �R���X�g���N�^
------------------------------------------------------------------- */
	/**
	 * ��̓��C����ʂ��\�z���܂��B
	 *
	 */
	public SrcCounter(){
		super();
		initComponents();
	}
/* -------------------------------------------------------------------
 �R���|�[�l���g�������y�ю���
------------------------------------------------------------------- */
	/**
	 * �R���|�[�l���g�������y�ю��������܂��B
	 *
	 */
	private void initComponents(){
		menubar.add(menu);
		menu.add(openItem);
		menu.add(csvItem);
		menu.addSeparator();
		menu.add(closeItem);
		setJMenuBar(menubar);

		JPanel panel = getMainPanel();
		panel.setLayout(new BorderLayout());

		table.setColumnNames(columnName);
		table.setPreferredColumnWidth(columnWidth);
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(1050, 500));
		scroll.setViewportView(table);
		panel.add(scroll, BorderLayout.CENTER);

		setEvent();

		pack();
		centerScreen();
		setVisible(true);
	}
/* -------------------------------------------------------------------
 �C�x���g�ݒ�
------------------------------------------------------------------- */
	/**
	 * �C�x���g��ݒ肵�܂��B
	 *
	 */
	private void setEvent(){
		// �J��
		openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				processParse();
				setCursor(Cursor.getDefaultCursor());
			}
		});
		// CSV�o��
		csvItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				processCsv();
				setCursor(Cursor.getDefaultCursor());
			}
		});
		// �I��
		closeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		// �E�C���h�D�N���[�Y
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
/* -------------------------------------------------------------------
 �t�@�C����͏���
------------------------------------------------------------------- */
	/**
	 * �t�@�C����͏����ł��B
	 *
	 */
	private void processParse(){
		Chooser chooser = new Chooser(this);
		boolean b = chooser.in();
		if(!b){
			return;
		}
		String type = chooser.getFilterType();
		File[] list = chooser.getFileList();
		table.clear();
		for(int i = 0; i < list.length; i++){
			Engineable engine = null;
			if(type.equals(JavaFilter.type)){
				engine = new JavaEngine(list[i]);
			}else if(type.equals(CFilter.type)){
				engine = new CEngine(list[i]);
			}else if(type.equals(CSharpFilter.type)){
				engine = new CSharpEngine(list[i]);
			}else{
				continue;
			}
			try{
				engine.parse();
				table.addRow(new Object[]{
					list[i].getAbsolutePath(),
					String.valueOf(engine.getLinage()),
					String.valueOf(engine.getStep()),
					String.valueOf(engine.getComment()),
					String.valueOf(engine.getEmpty()),
					String.valueOf(engine.getPrint()),
					String.valueOf(engine.getTrace()),
					String.valueOf(engine.getChange())
				});
			}catch(Exception e){
				table.addRow(new Object[]{
					list[i].getAbsolutePath(),
					"��͕s�\",
					"��͕s�\",
					"��͕s�\",
					"��͕s�\",
					"��͕s�\",
					"��͕s�\",
					"��͕s�\"
				});
			}
		}
	}
/* -------------------------------------------------------------------
 CSV�o�͏���
------------------------------------------------------------------- */
	/**
	 * CSV�o�͏����ł��B
	 *
	 */
	private void processCsv(){
		Chooser chooser = new Chooser(this);
		boolean b = chooser.out();
		if(!b){
			return;
		}
		File outFile = chooser.getOutFile();
		if(outFile == null){
			return;
		}
		if(outFile.exists()){
			int r = JOptionPane.showConfirmDialog(
						this,
						"�㏑�����Ă���낵���ł���",
						"�m�F",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
			if(r == JOptionPane.NO_OPTION){
				return;
			}
		}
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outFile)), true);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}

		StringBuffer column = new StringBuffer();
		for(int i = 0; i < columnName.length; i++){
			column.append(columnName[i]);
			if(i != (columnName.length - 1)){
				column.append(",");
			}
		}
		out.println(column.toString());

		SimpleTableModel model = (SimpleTableModel)table.getModel();
		for(int i = 0; i < model.getRowCount(); i++){
			StringBuffer sb = new StringBuffer();
			Object[] obj = table.getRow(i);
			int endLen = obj.length;
			for(int j = 0; j < obj.length; j++){
				String s = (String)obj[j];
				sb.append(s);
				if(j != (endLen - 1)){
					sb.append(",");
				}
			}
			out.println(sb.toString());
		}
		out.close();
	}
}
