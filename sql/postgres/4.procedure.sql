\c impacta

CREATE OR REPLACE FUNCTION prc_numero_extenso(IN p_numero INTEGER, OUT p_descricao TEXT) AS $$
DECLARE
    v_grupo_indice INTEGER;
    v_grupo_valor  INTEGER;
    v_centena      INTEGER;
    v_unidade      INTEGER;
    v_dezena       INTEGER;
BEGIN

    p_descricao := '';
    v_grupo_indice := 2;

    LOOP

        v_grupo_valor := MOD(DIV (p_numero, CAST(POWER(1000, v_grupo_indice) AS INTEGER)), 1000);
        v_centena := MOD(DIV(v_grupo_valor, 100), 10);
        v_dezena := MOD(DIV(v_grupo_valor, 10), 10);
        v_unidade := MOD(v_grupo_valor, 10);

        IF p_descricao <> '' AND v_grupo_valor > 0 THEN
            p_descricao := p_descricao || ', ';
        END IF;

        IF v_centena = 9 THEN
            p_descricao := p_descricao || 'novecentos';
        ELSIF v_centena = 8 THEN
            p_descricao := p_descricao || 'oitocentos';
        ELSIF v_centena = 7 THEN
            p_descricao := p_descricao || 'setecentos';
        ELSIF v_centena = 6 THEN
            p_descricao := p_descricao || 'seiscentos';
        ELSIF v_centena = 5 THEN
            p_descricao := p_descricao || 'quinhentos';
        ELSIF v_centena = 4 THEN
            p_descricao := p_descricao || 'quatrocentos';
        ELSIF v_centena = 3 THEN
            p_descricao := p_descricao || 'trezentos';
        ELSIF v_centena = 2 THEN
            p_descricao := p_descricao || 'duzentos';
        ELSIF v_centena = 1 THEN
            IF v_dezena > 0 OR v_unidade > 0 THEN
                p_descricao := p_descricao || 'cento';
            ELSE
                p_descricao := p_descricao || 'cem';
            END IF;
        END IF;

        IF p_descricao <> '' AND v_dezena > 0 THEN
            p_descricao := p_descricao || ' e ';
        END IF;

        IF v_dezena = 9 THEN
            p_descricao := p_descricao || 'noventa';
        ELSIF v_dezena = 8 THEN
            p_descricao := p_descricao || 'oitenta';
        ELSIF v_dezena = 7 THEN
            p_descricao := p_descricao || 'setenta';
        ELSIF v_dezena = 6 THEN
            p_descricao := p_descricao || 'sessenta';
        ELSIF v_dezena = 5 THEN
            p_descricao := p_descricao || 'cinquenta';
        ELSIF v_dezena = 4 THEN
            p_descricao := p_descricao || 'quarenta';
        ELSIF v_dezena = 3 THEN
            p_descricao := p_descricao || 'trinta';
        ELSIF v_dezena = 2 THEN
            p_descricao := p_descricao || 'vinte';
        ELSIF v_dezena = 1 THEN
            IF v_unidade = 9 THEN
                p_descricao := p_descricao || 'dezenove';
            ELSIF v_unidade = 8 THEN
                p_descricao := p_descricao || 'dezoito';
            ELSIF v_unidade = 7 THEN
                p_descricao := p_descricao || 'dezessete';
            ELSIF v_unidade = 6 THEN
                p_descricao := p_descricao || 'dezesseis';
            ELSIF v_unidade = 5 THEN
                p_descricao := p_descricao || 'quinze';
            ELSIF v_unidade = 4 THEN
                p_descricao := p_descricao || 'quatorze';
            ELSIF v_unidade = 3 THEN
                p_descricao := p_descricao || 'treze';
            ELSIF v_unidade = 2 THEN
                p_descricao := p_descricao || 'doze';
            ELSIF v_unidade = 1 THEN
                p_descricao := p_descricao || 'onze';
            ELSIF v_unidade = 0 THEN
                p_descricao := p_descricao || 'dez';
            END IF;
        END IF;

        IF v_dezena <> 1 THEN
            IF p_descricao <> '' AND v_unidade <> 0 THEN
                p_descricao := p_descricao || ' e ';
            END IF;

            IF v_unidade = 9 THEN
                p_descricao := p_descricao || 'nove';
            ELSIF v_unidade = 8 THEN
                p_descricao := p_descricao || 'oito';
            ELSIF v_unidade = 7 THEN
                p_descricao := p_descricao || 'sete';
            ELSIF v_unidade = 6 THEN
                p_descricao := p_descricao || 'seis';
            ELSIF v_unidade = 5 THEN
                p_descricao := p_descricao || 'cinco';
            ELSIF v_unidade = 4 THEN
                p_descricao := p_descricao || 'quatro';
            ELSIF v_unidade = 3 THEN
                p_descricao := p_descricao || 'tres';
            ELSIF v_unidade = 2 THEN
                p_descricao := p_descricao || 'dois';
            ELSIF v_unidade = 1 THEN
                p_descricao := p_descricao || 'um';
            END IF;
        END IF;

        IF v_grupo_indice = 2 AND v_grupo_valor > 1 THEN
            p_descricao := p_descricao || ' milhões';
        ELSIF v_grupo_indice = 2 AND v_grupo_valor = 1 THEN
            p_descricao := p_descricao || ' milhão';
        ELSIF v_grupo_indice = 1 AND v_grupo_valor > 0 THEN
            p_descricao := p_descricao || ' mil';
        END IF;

        v_grupo_indice := v_grupo_indice - 1;

        EXIT WHEN v_grupo_indice < 0;
    END LOOP;

    IF p_descricao = '' THEN
        p_descricao := 'zero';
    END IF;

END;
$$ LANGUAGE plpgsql;
