
package br.com.eugeniosolucoes.nfse.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inputXML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inputXML"
})
@XmlRootElement(name = "CancelarNfseRequest")
public class CancelarNfseRequest {

    protected String inputXML;

    /**
     * Obtém o valor da propriedade inputXML.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputXML() {
        return inputXML;
    }

    /**
     * Define o valor da propriedade inputXML.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputXML(String value) {
        this.inputXML = value;
    }

}
