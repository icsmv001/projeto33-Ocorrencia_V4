SELECT
   o.DATA,
   o.ID_OCORRENCIA,
   o.ID_SISTEMA,
   s.NM_SISTEMA,
   o.SINTOMA,
   o.INICIO,
   o.FIM,
   o.IMPACTO,
   o.CHAMADO,
   o.CAUSA,
   o.ACAO,
   o.OBSERVACOES,
   o.STATUS
FROM monitoracao_ambientes.ocorrencias  o
LEFT JOIN monitoracao_ambientes.sistemas s on (o.id_sistema = s.id_sistema)
;
