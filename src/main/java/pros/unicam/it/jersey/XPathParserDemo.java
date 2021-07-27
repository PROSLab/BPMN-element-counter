package pros.unicam.it.jersey;

import java.io.StringReader;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathParserDemo {

    public static void main(String[] args) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        		+ "<bpmn:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"Definitions_0vcsakx\" targetNamespace=\"http://bpmn.io/schema/bpmn\" exporter=\"bpmn-js (https://demo.bpmn.io)\" exporterVersion=\"8.7.0\">\n"
        		+ "  <bpmn:process id=\"Process_1k779uo\" isExecutable=\"false\">\n"
        		+ "    <bpmn:startEvent id=\"StartEvent_0ftgql8\">\n"
        		+ "      <bpmn:outgoing>Flow_1s5467f</bpmn:outgoing>\n"
        		+ "    </bpmn:startEvent>\n"
        		+ "    <bpmn:task id=\"Activity_1t1myr9\" name=\"2\">\n"
        		+ "      <bpmn:incoming>Flow_1s5467f</bpmn:incoming>\n"
        		+ "      <bpmn:outgoing>Flow_1hy85kx</bpmn:outgoing>\n"
        		+ "    </bpmn:task>\n"
        		+ "    <bpmn:sequenceFlow id=\"Flow_1s5467f\" sourceRef=\"StartEvent_0ftgql8\" targetRef=\"Activity_1t1myr9\" />\n"
        		+ "    <bpmn:task id=\"Activity_1k1ljq8\" name=\"3\">\n"
        		+ "      <bpmn:incoming>Flow_1hy85kx</bpmn:incoming>\n"
        		+ "      <bpmn:outgoing>Flow_0tuqpbu</bpmn:outgoing>\n"
        		+ "    </bpmn:task>\n"
        		+ "    <bpmn:sequenceFlow id=\"Flow_1hy85kx\" sourceRef=\"Activity_1t1myr9\" targetRef=\"Activity_1k1ljq8\" />\n"
        		+ "    <bpmn:task id=\"Activity_0jhoceq\" name=\"4\">\n"
        		+ "      <bpmn:incoming>Flow_0tuqpbu</bpmn:incoming>\n"
        		+ "      <bpmn:outgoing>Flow_0t0we6y</bpmn:outgoing>\n"
        		+ "    </bpmn:task>\n"
        		+ "    <bpmn:sequenceFlow id=\"Flow_0tuqpbu\" sourceRef=\"Activity_1k1ljq8\" targetRef=\"Activity_0jhoceq\" />\n"
        		+ "    <bpmn:endEvent id=\"Event_1wt0gjy\">\n"
        		+ "      <bpmn:incoming>Flow_0t0we6y</bpmn:incoming>\n"
        		+ "    </bpmn:endEvent>\n"
        		+ "    <bpmn:sequenceFlow id=\"Flow_0t0we6y\" sourceRef=\"Activity_0jhoceq\" targetRef=\"Event_1wt0gjy\" />\n"
        		+ "  </bpmn:process>\n"
        		+ "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n"
        		+ "    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1k779uo\">\n"
        		+ "      <bpmndi:BPMNEdge id=\"Flow_1s5467f_di\" bpmnElement=\"Flow_1s5467f\">\n"
        		+ "        <di:waypoint x=\"192\" y=\"120\" />\n"
        		+ "        <di:waypoint x=\"250\" y=\"120\" />\n"
        		+ "      </bpmndi:BPMNEdge>\n"
        		+ "      <bpmndi:BPMNEdge id=\"Flow_1hy85kx_di\" bpmnElement=\"Flow_1hy85kx\">\n"
        		+ "        <di:waypoint x=\"350\" y=\"120\" />\n"
        		+ "        <di:waypoint x=\"410\" y=\"120\" />\n"
        		+ "      </bpmndi:BPMNEdge>\n"
        		+ "      <bpmndi:BPMNEdge id=\"Flow_0tuqpbu_di\" bpmnElement=\"Flow_0tuqpbu\">\n"
        		+ "        <di:waypoint x=\"510\" y=\"120\" />\n"
        		+ "        <di:waypoint x=\"570\" y=\"120\" />\n"
        		+ "      </bpmndi:BPMNEdge>\n"
        		+ "      <bpmndi:BPMNEdge id=\"Flow_0t0we6y_di\" bpmnElement=\"Flow_0t0we6y\">\n"
        		+ "        <di:waypoint x=\"670\" y=\"120\" />\n"
        		+ "        <di:waypoint x=\"732\" y=\"120\" />\n"
        		+ "      </bpmndi:BPMNEdge>\n"
        		+ "      <bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_0ftgql8\">\n"
        		+ "        <dc:Bounds x=\"156\" y=\"102\" width=\"36\" height=\"36\" />\n"
        		+ "      </bpmndi:BPMNShape>\n"
        		+ "      <bpmndi:BPMNShape id=\"Activity_1t1myr9_di\" bpmnElement=\"Activity_1t1myr9\">\n"
        		+ "        <dc:Bounds x=\"250\" y=\"80\" width=\"100\" height=\"80\" />\n"
        		+ "      </bpmndi:BPMNShape>\n"
        		+ "      <bpmndi:BPMNShape id=\"Activity_1k1ljq8_di\" bpmnElement=\"Activity_1k1ljq8\">\n"
        		+ "        <dc:Bounds x=\"410\" y=\"80\" width=\"100\" height=\"80\" />\n"
        		+ "      </bpmndi:BPMNShape>\n"
        		+ "      <bpmndi:BPMNShape id=\"Activity_0jhoceq_di\" bpmnElement=\"Activity_0jhoceq\">\n"
        		+ "        <dc:Bounds x=\"570\" y=\"80\" width=\"100\" height=\"80\" />\n"
        		+ "      </bpmndi:BPMNShape>\n"
        		+ "      <bpmndi:BPMNShape id=\"Event_1wt0gjy_di\" bpmnElement=\"Event_1wt0gjy\">\n"
        		+ "        <dc:Bounds x=\"732\" y=\"102\" width=\"36\" height=\"36\" />\n"
        		+ "      </bpmndi:BPMNShape>\n"
        		+ "    </bpmndi:BPMNPlane>\n"
        		+ "  </bpmndi:BPMNDiagram>\n"
        		+ "</bpmn:definitions>\n"
        		+ "";
        System.out.println(xml);
        DocumentBuilderFactory domFactory = DocumentBuilderFactory
                .newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
                if("bpmn".equals(arg0)) {
                    return "http://www.omg.org/spec/BPMN/20100524/MODEL";
                }
                return null;
            }
        });
        // XPath Query for showing all nodes value

        try {
            XPathExpression expr = xpath
                    .compile("//bpmn:task");
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            System.out.println("Got " + nodes.getLength() + " nodes");
            System.out.println(nodes.item(0));
        } catch (Exception E) {
            System.out.println(E);
        }

    }
}