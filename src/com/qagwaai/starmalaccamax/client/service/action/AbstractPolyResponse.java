package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;

public abstract class AbstractPolyResponse extends AbstractResponse implements IsSerializable {
	private MimeType mimeType;

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(final MimeType mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return "AbstractPolyResponse [mimeType=" + mimeType + "]";
	}
	
	
}
