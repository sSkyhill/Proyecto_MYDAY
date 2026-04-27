namespace MYDAY
{
    partial class FormSubirPubli
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormSubirPubli));
            picImagen = new PictureBox();
            txtComentario = new TextBox();
            btnSubir = new Button();
            ((System.ComponentModel.ISupportInitialize)picImagen).BeginInit();
            SuspendLayout();
            // 
            // picImagen
            // 
            picImagen.Cursor = Cursors.Hand;
            picImagen.Image = (Image)resources.GetObject("picImagen.Image");
            picImagen.Location = new Point(90, 39);
            picImagen.Name = "picImagen";
            picImagen.Size = new Size(334, 225);
            picImagen.SizeMode = PictureBoxSizeMode.Zoom;
            picImagen.TabIndex = 0;
            picImagen.TabStop = false;
            picImagen.Click += pictureBox1_Click;
            // 
            // txtComentario
            // 
            txtComentario.BackColor = SystemColors.WindowFrame;
            txtComentario.Location = new Point(90, 289);
            txtComentario.Name = "txtComentario";
            txtComentario.Size = new Size(125, 27);
            txtComentario.TabIndex = 1;
            txtComentario.Text = "Comentario...";
            // 
            // btnSubir
            // 
            btnSubir.Location = new Point(311, 287);
            btnSubir.Name = "btnSubir";
            btnSubir.Size = new Size(94, 29);
            btnSubir.TabIndex = 2;
            btnSubir.Text = "Subir";
            btnSubir.UseVisualStyleBackColor = true;
            btnSubir.Click += btnSubir_Click;
            // 
            // FormSubirPubli
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.ControlDarkDark;
            ClientSize = new Size(503, 374);
            Controls.Add(btnSubir);
            Controls.Add(txtComentario);
            Controls.Add(picImagen);
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            Name = "FormSubirPubli";
            Text = "FormSubirPubli";
            ((System.ComponentModel.ISupportInitialize)picImagen).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private PictureBox picImagen;
        private TextBox txtComentario;
        private Button btnSubir;
    }
}