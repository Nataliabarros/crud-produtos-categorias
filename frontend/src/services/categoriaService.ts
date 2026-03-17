import api from "../api"

export const listarCategorias = async () => {
  const response = await api.get("/categorias")
  return response.data
}

export const criarCategoria = async (categoria: any) => {
  const response = await api.post("/categorias", categoria)
  return response.data
}