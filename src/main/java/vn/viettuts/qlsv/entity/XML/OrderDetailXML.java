package vn.viettuts.qlsv.entity.XML;

import vn.viettuts.qlsv.entity.OrderDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orderDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailXML {
    @XmlElement(name = "orderDetail")
    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
