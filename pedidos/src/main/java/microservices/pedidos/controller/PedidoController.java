package microservices.pedidos.controller;

import microservices.pedidos.model.Pedido;
import microservices.pedidos.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    public final RabbitTemplate rabbitTemplate;
    public final PedidoService pedidoService;

    public PedidoController (PedidoService pedidoService, RabbitTemplate rabbitTemplate){
        this.pedidoService = pedidoService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
       Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("",routingKey,pedidoSalvo.getDescricao());
        return "Pedido salvo e enviado para processamento: " +pedido.getDescricao();
    }


    @GetMapping
    public List<Pedido> listarPedido(){
        return pedidoService.listarPedidos();
    }
    


}
