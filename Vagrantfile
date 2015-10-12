# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|

  config.vm.synced_folder '.', '/vagrant', disabled: true

  config.vm.provider :digital_ocean do |provider, override|
    override.vm.box = 'digital_ocean'
    override.vm.box_url = 'https://github.com/smdahlen/vagrant-digitalocean/raw/master/box/digital_ocean.box'
    override.ssh.private_key_path = ENV['VAGRANT_SSH_KEY_PATH']

    provider.token = ENV['DIGITALOCEAN_TOKEN']
    provider.image = 13925467
    provider.region = 'sgp1'
    provider.ssh_key_name = ENV['DIGITALOCEAN_SSH_KEY_NAME']
    provider.size = '4gb'
  end
end
