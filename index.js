const crypto = require('crypto')

function ParamGenerator(songId, userId) {
  const bytes = Buffer.from(`songId=${songId}&userId=${userId}&timestamp=${Date.now()}`)
  const bytes2 = Buffer.from('indiMelonStmSvc!')
  const generateIv = crypto.randomBytes(16)
  const encrypt = crypto.createCipheriv('AES-128-CBC', bytes2, generateIv)
  return generateIv.toString('hex') + encrypt.update(bytes).toString('hex') + encrypt.final('hex')
}