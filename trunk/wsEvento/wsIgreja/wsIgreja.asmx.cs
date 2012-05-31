using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using MySql.Data.MySqlClient;
using System.Configuration;
using System.Data;
using System.Xml.Serialization;

namespace wsIgreja
{
    //[XmlRoot("Convidado")]
    public class Convidado
    {
        public int idConvidado { get; set; }
        public string nome { get; set; }
        public string email { get; set; }
        public string telefone { get; set; }
        public string origem { get; set; }
        public string funcao { get; set; }
    }

    [XmlRoot("Convidados")]
    public class Convidados : List<Convidado>
    { }

    /// <summary>
    /// Summary description for wsIgreja
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class wsIgreja : System.Web.Services.WebService
    {
        [WebMethod]
        public string InsertCliente(string nome, string email, string telefone, string origem, string funcao)
        {
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["connection"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand(SqlResources.InsertConvidado, cn))
                    {
                        cmd.Parameters.AddWithValue("nome", nome);
                        cmd.Parameters.AddWithValue("email", email);
                        cmd.Parameters.AddWithValue("telefone", telefone);
                        cmd.Parameters.AddWithValue("origem", origem);
                        cmd.Parameters.AddWithValue("funcao", funcao);

                        cmd.ExecuteNonQuery();
                        return "Convidado Incluído com sucesso.";
                    }
                }
            }
            catch (Exception ex)
            {
                return "Erro: " + ex.Message;
            }
        }

        [WebMethod]
        public Convidado Get(int idConvidado)
        {
            Convidado c = new Convidado();
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["connection"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand(SqlResources.GetConvidado, cn))
                    {
                        MySqlDataReader dr = null;
                        cmd.Parameters.AddWithValue("idConvidado", idConvidado);

                        dr = cmd.ExecuteReader(CommandBehavior.SingleRow);
                        if (dr.Read())
                        {
                            c.idConvidado   = dr.GetInt32(dr.GetOrdinal("CONV_ID_CONVIDADO"));
                            c.nome          = dr.GetString(dr.GetOrdinal("CONV_NOME"));
                            c.email         = dr.GetString(dr.GetOrdinal("CONV_EMAIL"));
                            c.origem        = dr.GetString(dr.GetOrdinal("CONV_ORIGEM"));
                            c.telefone      = dr.GetString(dr.GetOrdinal("CONV_TELEFONE"));
                            c.funcao        = dr.GetString(dr.GetOrdinal("CONV_FUNCAO"));
                        }
                        return c;
                    }
                }
            }
            catch
            {
                return new Convidado();
            }
        }

        [WebMethod]
        public Convidados List()
        {
            Convidados conv = new Convidados();
            try
            {
                using (MySqlConnection cn = new MySqlConnection(ConfigurationManager.ConnectionStrings["connection"].ConnectionString))
                {
                    cn.Open();
                    using (MySqlCommand cmd = new MySqlCommand(SqlResources.ListAll, cn))
                    {
                        MySqlDataReader dr = null;

                        dr = cmd.ExecuteReader(CommandBehavior.SingleResult);
                        while (dr.Read())
                        {
                            Convidado c = new Convidado();
                            c.idConvidado   = dr.GetInt32(dr.GetOrdinal("CONV_ID_CONVIDADO"));
                            c.nome          = dr.GetString(dr.GetOrdinal("CONV_NOME"));
                            c.email         = dr.GetString(dr.GetOrdinal("CONV_EMAIL"));
                            c.origem        = dr.GetString(dr.GetOrdinal("CONV_ORIGEM"));
                            c.telefone      = dr.GetString(dr.GetOrdinal("CONV_TELEFONE"));
                            c.funcao        = dr.GetString(dr.GetOrdinal("CONV_FUNCAO"));
                            conv.Add(c);
                        }
                        return conv;
                    }
                }
            }
            catch
            {
                return new Convidados();
            }
        }
    }
}
