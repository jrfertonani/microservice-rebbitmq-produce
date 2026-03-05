package microservices.pedidos.service;

import microservices.pedidos.model.ItemPedido;
import microservices.pedidos.model.Pedido;
import microservices.pedidos.repository.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidosRepository pedidosRepository;

    public PedidoService(PedidosRepository pedidosRepository){
        this.pedidosRepository = pedidosRepository;
    }

    public Pedido salvarPedido(Pedido pedido){
        if(pedido.getItens() != null){
            for(ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }
        return pedidosRepository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return pedidosRepository.findAll();
    }



}
