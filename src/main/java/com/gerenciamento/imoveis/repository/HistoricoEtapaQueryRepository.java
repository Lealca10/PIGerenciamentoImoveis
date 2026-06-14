package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.dto.HistoricoDuracaoAggregateDto;
import com.gerenciamento.imoveis.dto.HistoricoDuracaoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HistoricoEtapaQueryRepository {

    private final JdbcTemplate jdbc;

    public HistoricoEtapaQueryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<HistoricoDuracaoDto> findDurationsByImovelId(String imovelId) {
        String sql = "WITH events AS ("
                + " SELECT id, imovel_id, etapa_para::text AS etapa, data_alteracao,"
                + " LEAD(data_alteracao) OVER (PARTITION BY imovel_id ORDER BY data_alteracao) AS next_ts"
                + " FROM public.historico_etapas WHERE imovel_id = ?),"
                + " durations AS ("
                + " SELECT imovel_id, etapa, data_alteracao AS started_at,"
                + " CASE WHEN etapa = 'POS_VENDA' THEN data_alteracao ELSE COALESCE(next_ts, now()) END AS ended_at,"
                + " EXTRACT(epoch FROM ((CASE WHEN etapa = 'POS_VENDA' THEN data_alteracao ELSE COALESCE(next_ts, now()) END) - data_alteracao))/86400.0 AS days"
                + " FROM events)"
                + " SELECT imovel_id, etapa, started_at, ended_at, ROUND(days::numeric,3) AS days,"
                + " CASE etapa WHEN 'CADASTRO' THEN 1 WHEN 'LEILAO' THEN 2 WHEN 'NEGOCIACAO_AMIGAVEL' THEN 3 WHEN 'NEGOCIACAO_NAO_AMIGAVEL' THEN 4 WHEN 'JURIDICO' THEN 5 WHEN 'COMERCIAL' THEN 6 WHEN 'VENDA' THEN 7 WHEN 'POS_VENDA' THEN 8 ELSE 999 END AS etapa_order"
                + " FROM durations ORDER BY etapa_order, started_at";

        SqlRowSet rs = jdbc.queryForRowSet(sql, imovelId);
        List<HistoricoDuracaoDto> result = new ArrayList<>();
        while (rs.next()) {
            HistoricoDuracaoDto d = new HistoricoDuracaoDto();
            d.setImovelId(rs.getString("imovel_id"));
            d.setEtapa(rs.getString("etapa"));
            Timestamp s = rs.getTimestamp("started_at");
            if (s != null) d.setStartedAt(s.toLocalDateTime());
            Timestamp e = rs.getTimestamp("ended_at");
            if (e != null) d.setEndedAt(e.toLocalDateTime());
            double days = rs.getDouble("days");
            if (!rs.wasNull()) d.setDays(days);
            result.add(d);
        }
        return result;
    }

    public List<HistoricoDuracaoAggregateDto> findAggregatedByImovelId(String imovelId) {
        String sql = "WITH events AS ("
                + " SELECT imovel_id, etapa_para::text AS etapa, data_alteracao,"
                + " LEAD(data_alteracao) OVER (PARTITION BY imovel_id ORDER BY data_alteracao) AS next_ts"
                + " FROM public.historico_etapas WHERE imovel_id = ?),"
                + " durations AS ("
                + " SELECT imovel_id, etapa, EXTRACT(epoch FROM ((CASE WHEN etapa = 'POS_VENDA' THEN data_alteracao ELSE COALESCE(next_ts, now()) END) - data_alteracao))/86400.0 AS days"
                + " FROM events)"
                + " SELECT imovel_id, etapa, ROUND(SUM(days)::numeric,3) AS total_days,"
                + " CASE etapa WHEN 'CADASTRO' THEN 1 WHEN 'LEILAO' THEN 2 WHEN 'NEGOCIACAO_AMIGAVEL' THEN 3 WHEN 'NEGOCIACAO_NAO_AMIGAVEL' THEN 4 WHEN 'JURIDICO' THEN 5 WHEN 'COMERCIAL' THEN 6 WHEN 'VENDA' THEN 7 WHEN 'POS_VENDA' THEN 8 ELSE 999 END AS etapa_order"
                + " FROM durations GROUP BY imovel_id, etapa ORDER BY etapa_order";

        SqlRowSet rs = jdbc.queryForRowSet(sql, imovelId);
        List<HistoricoDuracaoAggregateDto> result = new ArrayList<>();
        while (rs.next()) {
            HistoricoDuracaoAggregateDto d = new HistoricoDuracaoAggregateDto();
            d.setImovelId(rs.getString("imovel_id"));
            d.setEtapa(rs.getString("etapa"));
            double total = rs.getDouble("total_days");
            if (!rs.wasNull()) d.setTotalDays(total);
            result.add(d);
        }
        return result;
    }
}
