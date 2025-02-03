# Grafo original G
G = Graph({
    "a": {"b": 4, "h": 8},
    "b": {"c": 8, "h": 11},
    "c": {"d": 7, "f": 4, "i": 2},
    "d": {"e": 9, "f": 14},
    "e": {"f": 10},
    "f": {"g": 2},
    "g": {"h": 1, "i": 6},
    "h": {"i": 7}
})

# Árvore Geradora Mínima T
T = Graph([
    ('g', 'h', 1),
    ('f', 'g', 2),
    ('c', 'i', 2),
    ('c', 'f', 4),
    ('a', 'b', 4),
    ('c', 'd', 7),
    ('b', 'c', 8),
    ('d', 'e', 9)
])

# Aresta a ser adicionada
aresta = ('b', 'e', 1)

# Função para calcular a árvore geradora mínima dinâmica
def arvore_geradora_minima_dinamica(G, T, aresta):
    # Adicionar a nova aresta ao grafo
    G.add_edge(aresta[0], aresta[1], aresta[2])
    
    # Verificar se a aresta adicionada cria um ciclo
    T_edges = T.edges(labels=True)  # Arestas da árvore geradora mínima T
    T_edges_set = set([(u, v) for u, v, _ in T_edges])  # Set de arestas
    
    if aresta[0] in T and aresta[1] in T:
        # Se as duas extremidades da aresta estão na árvore, temos um ciclo
        # Identificar o ciclo
        cycle = T.cycle_basis()
        cycle_edges = [(cycle[i], cycle[(i+1) % len(cycle)]) for i in range(len(cycle))]
        cycle_weights = {e: G[e[0], e[1]] for e in cycle_edges}
        
        # Identificar a aresta de maior peso no ciclo
        max_edge = max(cycle_edges, key=lambda e: cycle_weights[e])
        
        # Remover a aresta de maior peso
        T.delete_edge(max_edge[0], max_edge[1])
    
    # Adicionar a nova aresta à árvore
    T.add_edge(aresta[0], aresta[1], aresta[2])

    return T

# Chamada da função
T_atualizada = arvore_geradora_minima_dinamica(G, T, aresta)

# Mostrar a árvore geradora mínima atualizada
T_atualizada.show()
