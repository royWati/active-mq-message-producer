package chainbox.io.activemqproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/produce-message")
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public Producer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @GetMapping("/{msg}")
    public String sendMessage(@PathVariable String msg){
        jmsTemplate.convertAndSend(queue,msg);

        return "message send => "+msg;
    }
}
