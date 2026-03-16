import api from "../api";

export const listarProdutos = async () => {
  const response = await api.get("/produtos");
  return response.data;
};

export const criarProduto = async (produto: any) => {
  const response = await api.post("/produtos", produto);
  return response.data;
};

export const deletarProduto = async (id: number) => {
  await api.delete(`/produtos/${id}`);
};