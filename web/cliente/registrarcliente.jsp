<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CiberElectrik | Registrar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Registro de Cliente</h1>
        <form>
            <div class="row">
                <div class="col-4">
                    <label for="txtNom" class="form-label">Nombres:</label>
                    <input type="text" class="form-control" id="txtNom" name="txtNom" required>
                </div>
                <div class="col-4">
                    <label for="txtApe" class="form-label">Apellidos:</label>
                    <input type="text" class="form-control" id="txtApe" name="txtApe" required>
                </div>
                <div class="col-4">
                    <label for="cboTipoDoc" class="form-label">Tipo Documento:</label>
                    <select class="form-select" id="cboTipoDoc" name="cboTipoDoc" required>
                        <option value="0">Seleccione tipo</option>
                        <option value="1">DNI</option>
                        <option value="2">Pasaporte</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <label for="txtDoc" class="form-label">N° Documento:</label>
                    <input type="text" class="form-control" id="txtDoc" name="txtDoc" required>
                </div>
                <div class="col-4">
                    <label for="txtTel" class="form-label">Teléfono:</label>
                    <input type="text" class="form-control" id="txtTel" name="txtTel" required>
                </div>
                <div class="col-4">
                    <label for="txtCor" class="form-label">Correo:</label>
                    <input type="email" class="form-control" id="txtCor" name="txtCor" required>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <label for="cboSexo" class="form-label">Sexo:</label>
                    <select class="form-select" id="cboSexo" name="cboSexo" required>
                        <option value="0">Seleccione sexo</option>
                        <option value="1">Masculino</option>
                        <option value="2">Femenino</option>
                    </select>
                </div>
                <div class="col-4">
                    <label for="cboDistrito" class="form-label">Distrito:</label>
                    <select class="form-select" id="cboDistrito" name="cboDistrito" required>
                        <option value="0">Seleccione distrito</option>
                        <option value="1">Lima</option>
                        <option value="2">Miraflores</option>
                    </select>
                </div>
                <div class="col-4">
                    <label for="chkEst" class="form-label">Estado:</label>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true">
                        <label class="form-check-label" for="chkEst">Habilitado</label>
                    </div>
                </div>
            </div>
            <div class="mb-3"></div>
            <button type="submit" class="btn btn-primary">Registrar</button>
            <a href="listarcliente.jsp" class="btn btn-dark">Regresar</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>