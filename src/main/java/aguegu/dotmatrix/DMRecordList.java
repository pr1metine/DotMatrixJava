package aguegu.dotmatrix;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class DMRecordList extends JList<DMRecordFrame> {
	private static final long serialVersionUID = -8408932545525051039L;
	private final DMRecord dmr;
	private final DefaultListModel<DMRecordFrame> lm;

	public DMRecordList(DMRecord dmr) {
		this.dmr = dmr;
		this.lm = new DefaultListModel<>();

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.setModel(lm);
		this.setCellRenderer(new DotMatrixRecordCellRender());

		syncToRecord();
	}

	public void syncToRecord() {
		lm.clear();
		for (DMRecordFrame dmrf : this.dmr.getFrames()) {
			lm.addElement(dmrf);
		}
	}
	
	public int getNumFrames() {
		return lm.getSize();
	}

	static class DotMatrixRecordCellRender extends JLabel implements
			ListCellRenderer<DMRecordFrame> {
		private static final long serialVersionUID = 6448688964390706942L;

		public DotMatrixRecordCellRender() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(
				JList<? extends DMRecordFrame> list, DMRecordFrame value,
				int index, boolean isSelected, boolean cellHasFocus) {
			this.setText(Integer.toString(value.getIndex()));

			if (isSelected) {
				setBackground(Color.blue);
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}
			return this;
		}
	}

}
