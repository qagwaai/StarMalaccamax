/**
 * 
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;


/**
 * @author ehldxae
 *
 */
public class AbstractPolyAction implements IsSerializable {
	private MimeType mimeType;

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(final MimeType mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return "AbstractAction [mimeType=" + mimeType + "]";
	}

	public AbstractPolyAction(final MimeType mimeType) {
		super();
		this.mimeType = mimeType;
	}

	public AbstractPolyAction() {
		super();
	}

	
	
	
}
