import api from "../api"

export const listarCategorias = async () => {
  const response = await api.get("/categorias")
  return response.data
}