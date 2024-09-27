provider "aws" {
  region = "ap-south-1"
}

resource "aws_instance" "docker_instance" {
  ami           = "ami-0c2dc4b5c2c432587"  
  instance_type = "t2.micro"
  key_name      = "bashar-poc3"        

  tags = {
    Name = "DockerInstance"
  }

  vpc_security_group_ids = [aws_security_group.docker_sg.id]
}

resource "aws_security_group" "docker_sg" {
  name        = "docker_security_group"
  description = "Allow SSH and Docker ports"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks  = ["0.0.0.0/0"]  # Allow SSH from anywhere (change as needed)
  }

  ingress {
    from_port   = 2376
    to_port     = 2376
    protocol    = "tcp"
    cidr_blocks  = ["0.0.0.0/0"]  # Docker port, adjust as necessary
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"  # All protocols
    cidr_blocks  = ["0.0.0.0/0"]  # Allow all outbound traffic
  }
}
