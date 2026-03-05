package microservices.pedidos.controller;

import microservices.pedidos.model.Pedido;
import microservices.pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    public final PedidoService pedidoService;

    public PedidoController (PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }


    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
        pedidoService.salvarPedido(pedido);

        return "Pedido salvo e enviado para processamento: " +pedido.getDescricao();
    }


    @GetMapping
    public List<Pedido> listarPedido(){
        return pedidoService.listarPedidos();
    }
    


}
