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
 *         &lt;element name="Pedido" type="{http://www.abrasf.org.br/ABRASF/arquivos/nfse.xsd}tcPedidoCancelamento"/>
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
    "pedido"
})
@XmlRootElement(name = "CancelarNfseEnvio")
public class CancelarNfseEnvio {

    @XmlElement(name = "Pedido", required = true)
    protected TcPedidoCancelamento pedido;

    /**
     * Gets the value of the pedido property.
     * 
     * @return
     *     possible object is
     *     {@link TcPedidoCancelamento }
     *     
     */
    public TcPedidoCancelamento getPedido() {
        return pedido;
    }

    /**
     * Sets the value of the pedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcPedidoCancelamento }
     *     
     */
    public void setPedido(TcPedidoCancelamento value) {
        this.pedido = value;
    }

}
