package com.ge.predix.solsvc.bootstrap.ams.integration;

/**
 * 
 * 
 * @author 212421693
 */
public class RangeData {
	
    
	public final String range;
    
    public final int totalItems;
    
    public final int sizeReturned;
    
    public final String contentRange;
    
    public final int status;

    /**
     * @param range String 
     * @param totalItems integer
     * @param sizeReturned  integer
     * @param contentRange String
     * @param status integer
     */
    public RangeData(String range, int totalItems, int sizeReturned, String contentRange, int status) {
        this.contentRange = contentRange;
        this.totalItems = totalItems;
        this.range = range;
        this.sizeReturned = sizeReturned;
        this.status = status;
    }

    
    @Override
	public String toString() {
        return "Range " + this.range + " for total " + this.totalItems //$NON-NLS-1$ //$NON-NLS-2$
                + " should result in ContentRange " + this.contentRange //$NON-NLS-1$
                + "; size returned " + this.sizeReturned + "; Status code "  //$NON-NLS-1$//$NON-NLS-2$
                + this.status;
    }

	/**
	 * @return the range
	 */
	public String getRange() {
		return this.range;
	}

	/**
	 * @return the totalItems
	 */
	public int getTotalItems() {
		return this.totalItems;
	}

	/**
	 * @return the sizeReturned
	 */
	public int getSizeReturned() {
		return this.sizeReturned;
	}

	/**
	 * @return the contentRange
	 */
	public String getContentRange() {
		return this.contentRange;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}
}