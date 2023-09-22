package br.eti.davinunes.apiconselho.repository;

import java.util.List;

import br.eti.davinunes.apiconselho.entity.RelatorioData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MeuJRDataSource implements JRDataSource {
    private List<RelatorioData> dados;
    private int indiceRelatorioData = -1;
    private int indiceDetalhesTabela = -1;

    public MeuJRDataSource(List<RelatorioData> dados) {
        this.dados = dados;
    }

    @Override
    public boolean next() throws JRException {
        // Avança para o próximo RelatorioData se chegou ao fim da lista de DetalhesTabela
        if (indiceDetalhesTabela >= dados.get(indiceRelatorioData).getListaDeParametros().size() - 1) {
            indiceRelatorioData++;
            indiceDetalhesTabela = -1;
        }

        // Retorna verdadeiro se ainda houver mais RelatorioData na lista
        return indiceRelatorioData < dados.size();
    }

    @Override
    public Object getFieldValue(JRField campo) throws JRException {

        RelatorioData relatorioData = dados.get(indiceRelatorioData);

        if ("parametro1".equals(campo.getName())) {
            return relatorioData.getParametro1();
        } else if ("listaDeParametros".equals(campo.getName())) {
            // Avança para o próximo DetalhesTabela na lista
            indiceDetalhesTabela++;
            
            // Retorna o DetalhesTabela atual
            return relatorioData.getListaDeParametros().get(indiceDetalhesTabela);
        }
        // Lide com outros campos aqui
        return null;
    }
}
