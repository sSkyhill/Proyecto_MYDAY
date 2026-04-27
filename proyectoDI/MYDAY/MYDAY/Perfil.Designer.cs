namespace MYDAY
{
    partial class Perfil
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Perfil));
            panel1 = new Panel();
            picPerfil = new BotonCircular();
            picSubir = new PictureBox();
            lblPerfil = new Label();
            lblImagen = new Label();
            pictureBox1 = new PictureBox();
            btnPerfil = new Button();
            flowPerfil = new FlowLayoutPanel();
            panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)picSubir).BeginInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox1).BeginInit();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.BackColor = Color.Gray;
            panel1.Controls.Add(picPerfil);
            panel1.Controls.Add(picSubir);
            panel1.Controls.Add(lblPerfil);
            panel1.Controls.Add(lblImagen);
            panel1.Controls.Add(pictureBox1);
            panel1.Controls.Add(btnPerfil);
            panel1.Dock = DockStyle.Top;
            panel1.Location = new Point(0, 0);
            panel1.Name = "panel1";
            panel1.Size = new Size(800, 66);
            panel1.TabIndex = 0;
            // 
            // picPerfil
            // 
            picPerfil.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            picPerfil.BackgroundImage = (Image)resources.GetObject("picPerfil.BackgroundImage");
            picPerfil.Cursor = Cursors.Hand;
            picPerfil.Location = new Point(698, 28);
            picPerfil.Name = "picPerfil";
            picPerfil.Size = new Size(34, 30);
            picPerfil.TabIndex = 6;
            // 
            // picSubir
            // 
            picSubir.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            picSubir.Cursor = Cursors.Hand;
            picSubir.Image = (Image)resources.GetObject("picSubir.Image");
            picSubir.Location = new Point(541, 30);
            picSubir.Name = "picSubir";
            picSubir.Size = new Size(68, 33);
            picSubir.SizeMode = PictureBoxSizeMode.Zoom;
            picSubir.TabIndex = 4;
            picSubir.TabStop = false;
            // 
            // lblPerfil
            // 
            lblPerfil.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            lblPerfil.AutoSize = true;
            lblPerfil.Cursor = Cursors.Hand;
            lblPerfil.Location = new Point(694, 7);
            lblPerfil.Name = "lblPerfil";
            lblPerfil.Size = new Size(42, 20);
            lblPerfil.TabIndex = 3;
            lblPerfil.Text = "Perfil";
            // 
            // lblImagen
            // 
            lblImagen.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            lblImagen.AutoSize = true;
            lblImagen.Cursor = Cursors.Hand;
            lblImagen.Location = new Point(528, 7);
            lblImagen.Name = "lblImagen";
            lblImagen.Size = new Size(97, 20);
            lblImagen.TabIndex = 2;
            lblImagen.Text = "Subir Imagen";
            // 
            // pictureBox1
            // 
            pictureBox1.Cursor = Cursors.Hand;
            pictureBox1.Image = Properties.Resources.Copilot_20260119_170732;
            pictureBox1.Location = new Point(3, -38);
            pictureBox1.Name = "pictureBox1";
            pictureBox1.Size = new Size(195, 145);
            pictureBox1.SizeMode = PictureBoxSizeMode.Zoom;
            pictureBox1.TabIndex = 1;
            pictureBox1.TabStop = false;
            // 
            // btnPerfil
            // 
            btnPerfil.FlatAppearance.BorderSize = 0;
            btnPerfil.FlatStyle = FlatStyle.Flat;
            btnPerfil.Location = new Point(694, 7);
            btnPerfil.Name = "btnPerfil";
            btnPerfil.Size = new Size(42, 20);
            btnPerfil.TabIndex = 0;
            btnPerfil.UseVisualStyleBackColor = true;
            // 
            // flowPerfil
            // 
            flowPerfil.AutoScroll = true;
            flowPerfil.Dock = DockStyle.Fill;
            flowPerfil.FlowDirection = FlowDirection.TopDown;
            flowPerfil.Location = new Point(0, 66);
            flowPerfil.Name = "flowPerfil";
            flowPerfil.Size = new Size(800, 384);
            flowPerfil.TabIndex = 1;
            flowPerfil.WrapContents = false;
            // 
            // Perfil
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(64, 64, 64);
            ClientSize = new Size(800, 450);
            Controls.Add(flowPerfil);
            Controls.Add(panel1);
            Name = "Perfil";
            Text = "Perfil";
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)picSubir).EndInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox1).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private Panel panel1;
        private Button btnPerfil;
        private PictureBox pictureBox1;
        private FlowLayoutPanel flowPerfil;
        private Label lblImagen;
        private Label lblPerfil;
        private PictureBox picSubir;
        private BotonCircular picPerfil;
    
       

        
    }
}