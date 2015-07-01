package com.wmv.poc.jpa.poc;

import javax.xml.bind.annotation.*;

/**
 * @author wvergara, created on 6/30/15.
 */

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="fields")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductField {

    @XmlElement(
            name = "product-id",
            required = true
    )
    private String productId;
    @XmlElement(
            name = "reference-order-id",
            required = true
    )
    private String referenceOrderId;
    @XmlElement(
            name = "request-reason",
            required = true
    )
    private String requestReason;
    @XmlElement(
            name = "owens-username",
            required = true
    )
    private String owensUsername;


    public ProductField() {
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String value) {
        this.productId = value;
    }

    public String getReferenceOrderId() {
        return this.referenceOrderId;
    }

    public void setReferenceOrderId(String value) {
        this.referenceOrderId = value;
    }

    public String getRequestReason() {
        return this.requestReason;
    }

    public void setRequestReason(String value) {
        this.requestReason = value;
    }

    public String getOwensUsername() {
        return this.owensUsername;
    }

    public void setOwensUsername(String value) {
        this.owensUsername = value;
    }
}


