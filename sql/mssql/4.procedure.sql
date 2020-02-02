ALTER PROCEDURE prc_numero_extenso
    @p_numero NUMERIC,
    @p_descricao VARCHAR(100) OUT
AS
BEGIN

    DECLARE @v_grupo_indice NUMERIC;
    DECLARE @v_grupo_valor  NUMERIC;
    DECLARE @v_centena      NUMERIC;
    DECLARE @v_unidade      NUMERIC;
    DECLARE @v_dezena       NUMERIC;

    SET @p_descricao = '';
    SET @v_grupo_indice = 2;

    WHILE @v_grupo_indice >= 0 BEGIN

        SET @v_grupo_valor = (@p_numero / POWER(1000, @v_grupo_indice)) % 1000;
        SET @v_centena = (@v_grupo_valor / 100) % 10;
        SET @v_dezena = (@v_grupo_valor / 10) % 10;
        SET @v_unidade = @v_grupo_valor % 10;

        IF @p_descricao <> '' AND @v_grupo_valor > 0 BEGIN
            SET @p_descricao = @p_descricao + ', ';
        END

        IF @v_centena = 9
            SET @p_descricao = @p_descricao + 'novecentos';
        ELSE IF @v_centena = 8
            SET @p_descricao = @p_descricao + 'oitocentos';
        ELSE IF @v_centena = 7
            SET @p_descricao = @p_descricao + 'setecentos';
        ELSE IF @v_centena = 6
            SET @p_descricao = @p_descricao + 'seiscentos';
        ELSE IF @v_centena = 5
            SET @p_descricao = @p_descricao + 'quinhentos';
        ELSE IF @v_centena = 4
            SET @p_descricao = @p_descricao + 'quatrocentos';
        ELSE IF @v_centena = 3
            SET @p_descricao = @p_descricao + 'trezentos';
        ELSE IF @v_centena = 2
            SET @p_descricao = @p_descricao + 'duzentos';
        ELSE IF @v_centena = 1 BEGIN
            IF @v_dezena > 0 OR @v_unidade > 0
                SET @p_descricao = @p_descricao + 'cento';
            ELSE
                SET @p_descricao = @p_descricao + 'cem';
        END

        IF @p_descricao <> '' AND @v_dezena > 0 BEGIN
            SET @p_descricao = @p_descricao + ' e ';
        END

        IF @v_dezena = 9
            SET @p_descricao = @p_descricao + 'noventa';
        ELSE IF @v_dezena = 8
            SET @p_descricao = @p_descricao + 'oitenta';
        ELSE IF @v_dezena = 7
            SET @p_descricao = @p_descricao + 'setenta';
        ELSE IF @v_dezena = 6
            SET @p_descricao = @p_descricao + 'sessenta';
        ELSE IF @v_dezena = 5
            SET @p_descricao = @p_descricao + 'cinquenta';
        ELSE IF @v_dezena = 4
            SET @p_descricao = @p_descricao + 'quarenta';
        ELSE IF @v_dezena = 3
            SET @p_descricao = @p_descricao + 'trinta';
        ELSE IF @v_dezena = 2
            SET @p_descricao = @p_descricao + 'vinte';
        ELSE IF @v_dezena = 1 BEGIN
            IF @v_unidade = 9
                SET @p_descricao = @p_descricao + 'dezenove';
            ELSE IF @v_unidade = 8
                SET @p_descricao = @p_descricao + 'dezoito';
            ELSE IF @v_unidade = 7
                SET @p_descricao = @p_descricao + 'dezessete';
            ELSE IF @v_unidade = 6
                SET @p_descricao = @p_descricao + 'dezesseis';
            ELSE IF @v_unidade = 5
                SET @p_descricao = @p_descricao + 'quinze';
            ELSE IF @v_unidade = 4
                SET @p_descricao = @p_descricao + 'quatorze';
            ELSE IF @v_unidade = 3
                SET @p_descricao = @p_descricao + 'treze';
            ELSE IF @v_unidade = 2
                SET @p_descricao = @p_descricao + 'doze';
            ELSE IF @v_unidade = 1
                SET @p_descricao = @p_descricao + 'onze';
            ELSE IF @v_unidade = 0
                SET @p_descricao = @p_descricao + 'dez';
        END

        IF @v_dezena <> 1 BEGIN
            IF @p_descricao <> '' AND @v_unidade <> 0 BEGIN
                SET @p_descricao = @p_descricao + ' e ';
            END

            IF @v_unidade = 9
                SET @p_descricao = @p_descricao + 'nove';
            ELSE IF @v_unidade = 8
                SET @p_descricao = @p_descricao + 'oito';
            ELSE IF @v_unidade = 7
                SET @p_descricao = @p_descricao + 'sete';
            ELSE IF @v_unidade = 6
                SET @p_descricao = @p_descricao + 'seis';
            ELSE IF @v_unidade = 5
                SET @p_descricao = @p_descricao + 'cinco';
            ELSE IF @v_unidade = 4
                SET @p_descricao = @p_descricao + 'quatro';
            ELSE IF @v_unidade = 3
                SET @p_descricao = @p_descricao + 'tres';
            ELSE IF @v_unidade = 2
                SET @p_descricao = @p_descricao + 'dois';
            ELSE IF @v_unidade = 1
                SET @p_descricao = @p_descricao + 'um';
        END

        IF @v_grupo_indice = 2 AND @v_grupo_valor > 1
            SET @p_descricao = @p_descricao + ' milhões';
        ELSE IF @v_grupo_indice = 2 AND @v_grupo_valor = 1
            SET @p_descricao = @p_descricao + ' milhão';
        ELSE IF @v_grupo_indice = 1 AND @v_grupo_valor > 0
            SET @p_descricao = @p_descricao + ' mil';

        SET @v_grupo_indice = @v_grupo_indice - 1;

    END

    IF @p_descricao = '' BEGIN
        SET @p_descricao = 'zero';
    END

END
GO
