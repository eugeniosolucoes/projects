//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.30 at 08:33:20 PM BRT 
//


package br.com.eugeniosolucoes.nfse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoteRps" type="{http://www.abrasf.org.br/ABRASF/arquivos/nfse.xsd}tcLoteRps"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loteRps",
    "signature"
})
@XmlRootElement(name = "EnviarLoteRpsEnvio")
public class EnviarLoteRpsEnvio {

    @XmlElement(name = "LoteRps", required = true)
    protected TcLoteRps loteRps;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;

    /**
     * Gets the value of the loteRps property.
     * 
     * @return
     *     possible object is
     *     {@link TcLoteRps }
     *     
     */
    public TcLoteRps getLoteRps() {
        if ( loteRps == null ) {
            loteRps = new TcLoteRps();
        }
        return loteRps;
    }

    /**
     * Sets the value of the loteRps property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcLoteRps }
     *     
     */
    public void setLoteRps(TcLoteRps value) {
        this.loteRps = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

}
