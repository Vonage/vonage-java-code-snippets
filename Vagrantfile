Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.provision "shell", inline: <<PROV
    echo $USER
    apt-get install -y zip unzip \
    && cat > ~vagrant/install_script.sh <<'EOS';
      curl -s "https://get.sdkman.io" | bash \
      && mkdir -p ~/.sdkman/etc \
      && echo "HOME: " ~ \
      && echo 'sdkman_auto_answer=true' > ~/.sdkman/etc/config \
      && source ~/.sdkman/bin/sdkman-init.sh \
      && yes | sdk install java 8u121 2>/dev/null \
      && yes | sdk install gradle 3.1 2>/dev/null \
      && echo "Provisioning complete. Now run 'vagrant ssh', cd to /vagrant, and run 'gradle test'"
EOS
    chown vagrant:vagrant ~vagrant/install_script.sh
    sudo -iu vagrant source ~vagrant/install_script.sh
PROV
end
