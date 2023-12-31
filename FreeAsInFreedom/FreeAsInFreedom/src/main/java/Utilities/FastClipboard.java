package Utilities;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class FastClipboard {
	public FastClipboard() {
		
	}
	
	public String ClipGet() {
		try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (Exception e) {}
		return null;
	}
	
	public void ClipPut(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	
	public Image getImageFromClipboard() {
		  Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		  if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)){
		      try {
				return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
			} catch (Exception e) { }
		  }
		  return null;
	}
}
