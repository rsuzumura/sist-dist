using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using MySql.Data.MySqlClient;
using System.Configuration;
using System.Data;

namespace wsEvento
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class ServiceEvento : System.Web.Services.WebService
    {
        #region Convidados
        public class Convidado
        {
            public int ID { get; set; }
            public string Nome { get; set; }
            public string Email { get; set; }
            public string Telefone { get; set; }
            public string Origem { get; set; }
        }
        
        [WebMethod]
        public Convidado ObterConvidado(int idConvidado)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    Convidado conv = new Convidado();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_CONVIDADO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WCONV_ID", idConvidado);
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleRow))
                        {
                            if (dr.Read())
                            {
                                conv.ID = dr.GetInt32(0);
                                conv.Nome = dr.GetString(1);
                                conv.Email = dr.GetString(2);
                                conv.Telefone = dr.GetString(3);
                                conv.Origem = dr.GetString(4);
                            }
                        }
                    }
                    return conv;
                }
            }
            catch
            {
                return null;
            }
        }
            
        [WebMethod]
        public List<Convidado> ObterConvidados()
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Convidado> result = new List<Convidado>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_CONVIDADOS", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Convidado conv = new Convidado();
                                conv.ID = dr.GetInt32(0);
                                conv.Nome = dr.GetString(1);
                                conv.Email = dr.GetString(2);
                                conv.Telefone = dr.GetString(3);
                                conv.Origem = dr.GetString(4);
                                result.Add(conv);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public List<Convidado> ObterConvidadosPorEvento(int idEvento)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Convidado> result = new List<Convidado>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_CONVIDADO_POR_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WEVEN_ID", idEvento);
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Convidado conv = new Convidado();
                                conv.ID = dr.GetInt32(0);
                                conv.Nome = dr.GetString(1);
                                conv.Email = dr.GetString(2);
                                conv.Telefone = dr.GetString(3);
                                conv.Origem = dr.GetString(4);
                                result.Add(conv);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public int IncluirConvidado(string nome, string email, string telefone, string origem)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    int result = 0;
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("INCLUIR_CONVIDADO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WCONV_NOME", nome);
                        cmd.Parameters.AddWithValue("WCONV_EMAIL", email);
                        cmd.Parameters.AddWithValue("WCONV_TELEFONE", telefone);
                        cmd.Parameters.AddWithValue("WCONV_ORIGEM", origem);
                        result = Convert.ToInt32(cmd.ExecuteScalar());
                    }
                    return result;
                }
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod]
        public bool AlterarConvidado(int id, string nome, string email, string telefone, string origem)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("ALTERAR_CONVIDADO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WCONV_ID", id);
                        cmd.Parameters.AddWithValue("WCONV_NOME", nome);
                        cmd.Parameters.AddWithValue("WCONV_EMAIL", email);
                        cmd.Parameters.AddWithValue("WCONV_TELEFONE", telefone);
                        cmd.Parameters.AddWithValue("WCONV_ORIGEM", origem);
                        cmd.ExecuteNonQuery();
                    }
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }
        #endregion

        #region Funções

        public class Funcao
        {
            public int ID { get; set; }
            public string Nome { get; set; }
        }
                
        [WebMethod]
        public List<Funcao> ObterFuncoes()
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Funcao> result = new List<Funcao>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_FUNCOES", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Funcao func = new Funcao();
                                func.ID = dr.GetInt32(0);
                                func.Nome = dr.GetString(1);
                                result.Add(func);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public int IncluirFuncao(string nome)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    int result = 0;
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("INCLUIR_FUNCAO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WFUNC_NOME", nome);
                        result = Convert.ToInt32(cmd.ExecuteScalar());
                    }
                    return result;
                }
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod]
        public bool AlterarFuncao(int id, string nome)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("ALTERAR_FUNCAO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WFUNC_ID", id);
                        cmd.Parameters.AddWithValue("WFUNC_NOME", nome);
                        cmd.ExecuteNonQuery();
                    }
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }

        #endregion

        #region Tipos de Evento

        public class TipoEvento
        {
            public int ID { get; set; }
            public string Nome { get; set; }
        }

        [WebMethod]
        public List<TipoEvento> ObterTiposEvento()
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<TipoEvento> result = new List<TipoEvento>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_TIPOS_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                TipoEvento te = new TipoEvento();
                                te.ID = dr.GetInt32(0);
                                te.Nome = dr.GetString(1);
                                result.Add(te);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public int IncluirTipoEvento(string nome)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    int result = 0;
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("INCLUIR_TIPO_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WTPEV_NOME", nome);
                        result = Convert.ToInt32(cmd.ExecuteScalar());
                    }
                    return result;
                }
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod]
        public bool AlterarTipoEvento(int id, string nome)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("ALTERAR_TIPO_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WTPEV_ID", id);
                        cmd.Parameters.AddWithValue("WTPEV_NOME", nome);
                        cmd.ExecuteNonQuery();
                    }
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }

        #endregion

        #region Evento

        public class Evento
        {
            public int ID { get; set; }
            public string Nome { get; set; }
            public string Local { get; set; }
            public string Responsavel { get; set; }
            public DateTime InicioEvento { get; set; }
            public DateTime FimEvento { get; set; }
            public string Status { get; set; }
            public int TipoEventoId { get; set; }
        }

        [WebMethod]
        public List<Evento> ObterEventos()
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Evento> result = new List<Evento>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_EVENTOS", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Evento ev = new Evento();
                                ev.ID = dr.GetInt32(0);
                                ev.Nome = dr.GetString(1);
                                ev.Local = dr.GetString(2);
                                ev.Responsavel = dr.GetString(3);
                                ev.InicioEvento = dr.GetDateTime(4);
                                ev.FimEvento = dr.GetDateTime(5);
                                ev.Status = dr.GetString(6);
                                ev.TipoEventoId = dr.GetInt32(7);
                                result.Add(ev);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public List<Evento> ObterEventosPorTipoEvento(int tipoEventoId)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Evento> result = new List<Evento>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_EVENTOS_POR_TIPO_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WTPEV_ID", tipoEventoId);
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Evento ev = new Evento();
                                ev.ID = dr.GetInt32(0);
                                ev.Nome = dr.GetString(1);
                                ev.Local = dr.GetString(2);
                                ev.Responsavel = dr.GetString(3);
                                ev.InicioEvento = dr.GetDateTime(4);
                                ev.FimEvento = dr.GetDateTime(5);
                                ev.Status = dr.GetString(6);
                                ev.TipoEventoId = dr.GetInt32(7);
                                result.Add(ev);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public List<Evento> ObterEventosPorConvidado(int convidadoId)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Evento> result = new List<Evento>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_EVENTOS_POR_TIPO_CONVIDADO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WCONV_ID", convidadoId);
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Evento ev = new Evento();
                                ev.ID = dr.GetInt32(0);
                                ev.Nome = dr.GetString(1);
                                ev.Local = dr.GetString(2);
                                ev.Responsavel = dr.GetString(3);
                                ev.InicioEvento = dr.GetDateTime(4);
                                ev.FimEvento = dr.GetDateTime(5);
                                ev.Status = dr.GetString(6);
                                ev.TipoEventoId = dr.GetInt32(7);
                                result.Add(ev);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public List<Evento> ObterEventosPorNome(string nome)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    List<Evento> result = new List<Evento>();
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("OBTER_EVENTOS_POR_NOME", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WEVEN_NOME", nome);
                        using (MySqlDataReader dr = cmd.ExecuteReader(CommandBehavior.SingleResult))
                        {
                            while (dr.Read())
                            {
                                Evento ev = new Evento();
                                ev.ID = dr.GetInt32(0);
                                ev.Nome = dr.GetString(1);
                                ev.Local = dr.GetString(2);
                                ev.Responsavel = dr.GetString(3);
                                ev.InicioEvento = dr.GetDateTime(4);
                                ev.FimEvento = dr.GetDateTime(5);
                                ev.Status = dr.GetString(6);
                                ev.TipoEventoId = dr.GetInt32(7);
                                result.Add(ev);
                            }
                        }
                    }
                    return result;
                }
            }
            catch
            {
                return null;
            }
        }

        [WebMethod]
        public int IncluirEvento(string nome, string local, string responsavel, DateTime inicioEvento, DateTime fimEvento, string status, int tipoEventoId)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    int result = 0;
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("INCLUIR_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WEVEN_NOME", nome);
                        cmd.Parameters.AddWithValue("WEVEN_LOCAL", local);
                        cmd.Parameters.AddWithValue("WEVEN_RESPONSAVEL", responsavel);
                        cmd.Parameters.AddWithValue("WEVEN_INICIO", inicioEvento);
                        cmd.Parameters.AddWithValue("WEVEN_FIM", fimEvento);
                        cmd.Parameters.AddWithValue("WEVEN_STATUS", status);
                        cmd.Parameters.AddWithValue("WEVEN_TPEV_ID", tipoEventoId);
                        result = Convert.ToInt32(cmd.ExecuteScalar());
                    }
                    return result;
                }
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod]
        public bool AlterarEvento(int id, string nome, string local, string responsavel, DateTime inicioEvento, DateTime fimEvento, string status, int tipoEventoId)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand("ALTERAR_EVENTO", cn))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("WEVEN_ID", id);
                        cmd.Parameters.AddWithValue("WEVEN_NOME", nome);
                        cmd.Parameters.AddWithValue("WEVEN_LOCAL", local);
                        cmd.Parameters.AddWithValue("WEVEN_RESPONSAVEL", responsavel);
                        cmd.Parameters.AddWithValue("WEVEN_INICIO", inicioEvento);
                        cmd.Parameters.AddWithValue("WEVEN_FIM", fimEvento);
                        cmd.Parameters.AddWithValue("WEVEN_STATUS", status);
                        cmd.Parameters.AddWithValue("WEVEN_TPEV_ID", tipoEventoId);
                        cmd.ExecuteNonQuery();
                    }
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }

        #endregion
    }
}
