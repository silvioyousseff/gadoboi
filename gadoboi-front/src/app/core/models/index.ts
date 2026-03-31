export interface Endereco {
  id?: number;
  bairro: string;
  numero: string;
  complemento?: string;
  cidade: string;
  estado: string;
}

export interface Cliente {
  id?: number;
  nome: string;
  sobrenome: string;
  email: string;
  cpf: string;
  rg?: string;
  telefone?: string;
  celular?: string;
  sexo?: 'M' | 'F';
  dataNasc?: string;
  endereco?: Endereco;
}

export interface Gado {
  id?: number;
  nomeGado: string;
}

export interface Insumo {
  id?: number;
  nomeInsumo: string;
}

export interface Manejo {
  id?: number;
  nomeManejo: string;
}

export interface Regiao {
  id?: number;
  nomeRegiao: string;
}

export interface RacaoInsumo {
  id?: number;
  insumoId: number;
  nomeInsumo?: string;
  qtdInsumo: number;
}

export interface Racao {
  id?: number;
  nomeRacao: string;
  taxaRendimento?: number;
  data?: string;
  horario?: string;
  status?: boolean;
  qtdGado?: number;
  qtdTratamentoDia?: number;
  pesoInicial?: number;
  gadoId?: number;
  regiaoId?: number;
  manejoId?: number;
  insumos: RacaoInsumo[];
  metodos: string[];
}

export interface RendimentoRequest {
  qtdAnimais: number;
  periodoDeTratamento: number;
  tamanhoDaPastagem: number;
  pesoInicial: number;
  ganhoDePesoEsperado: number;
  rendimentoCarcaca: number;
  precoArroba: number;
  precoPorQuiloCon: number;
}

export interface Rendimento extends RendimentoRequest {
  id?: number;
  taxaLotacao: number;
  qtdConcentradoDiariamente: number;
  qtdConcentradoTotal: number;
  precoConcentradoDiaAnimal: number;
  ganhoPorCabecaDia: number;
  custoTotal: number;
  ganhoTotal: number;
  resultado: number;
}

export interface AuthRequest {
  email: string;
  senha: string;
}

export interface AuthResponse {
  token: string;
}
